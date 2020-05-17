(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
'use strict'; module.exports = angular.module("tw-currency-select-templates", []).run(["$templateCache", function($templateCache) {$templateCache.put("templates/currencySelect.html","<select\n        class=\"selectpicker currency-select\"\n        data-live-search=\"{{vm.useSearch}}\"\n        data-live-search-placeholder=\"{{vm.calculatedSearchPlaceholder}}\"\n        data-none-results-text=\"{{vm.calculatedNoResultsText}}\"\n        data-none-selected-text=\"{{vm.calculatedNoneSelectedText}}\"\n        data-style=\"btn-input\">\n    <option data-hidden=\"true\"></option>\n    <option ng-repeat=\"currency in vm.mappedCurrencies track by currency.code\"\n            data-content=\n                    \"<div class=\'currency-select__flag-currency-code\'>\n                        <div class=\'currency-select__flag-wrapper\'>\n                            <div class=\'currency-select__dropdown-flag currency-select__flag24__{{vm.flagCode(currency.code)}}\'></div>\n                        </div>\n                        <span class=\'currency-select__currency-code\'>{{currency.code}}</span>\n                        <span class=\'currency-select__currency-name {{vm.hideNameClasses}}\'>{{currency.name}}</span>\n                    </div>\"\n            value=\"{{currency.code}}\">\n        {{currency.code}}\n    </option>\n</select>\n");}]);
},{}],2:[function(require,module,exports){
(function (global){
'use strict';

var angular = (typeof window !== "undefined" ? window['angular'] : typeof global !== "undefined" ? global['angular'] : null);
var currencyCountryMap = require('./currencyCountryMap');
var constants = require('./constants');

module.exports = function CurrencySelectController($scope, $timeout) {
    var vm = this;
    var currencyMap;
    vm.flagCode = flagCode;
    vm.onChangeHandler = mappedModelChangedHandler;

    init();

    function init() {
        initMapperAndExtractor();
        initCurrencies();
        initMappedCurrencies();
        initMappedModel();
        initSearch();
        initNoneSelectedText();
        initHideNameClasses();
        initWatchers();
    }

    function initMapperAndExtractor() {
        vm.extractor = (typeof vm.extractor === 'function') ? vm.extractor : defaultExtractorAndMapper;
        vm.mapper = (typeof vm.mapper === 'function') ? vm.mapper : defaultExtractorAndMapper;
    }

    function initCurrencies() {
        vm.currencies = (vm.currencies) ? vm.currencies : [];
    }

    function initMappedCurrencies() {
        currencyMap = {};
        vm.mappedCurrencies = vm.currencies.map(vm.mapper);
        vm.mappedCurrencies.forEach(function(x) {
            currencyMap[x.code] = x;
        });
    }

    function initMappedModel() {
        vm.mappedModel = (vm.ngModel) ? vm.mapper(vm.ngModel) : undefined;
    }

    function initSearch() {
        vm.useSearch = vm.noSearch !== constants.ATTR_NO_SEARCH;

        vm.calculatedSearchPlaceholder = vm.searchPlaceholder;

        vm.calculatedNoResultsText = vm.noResultsText;
        if (vm.noResultsText === '' || vm.noResultsText === '0' || (!vm.noResultsText)) {
            vm.calculatedNoResultsText = constants.DEFAULT_NO_RESULTS_PLACEHOLDER;
        }
    }

    function initNoneSelectedText() {
        vm.calculatedNoneSelectedText = vm.noneSelectedText || '';
    }

    function initHideNameClasses() {
        var hideNameClasses = [];
        if (vm.hideNameOptions && vm.hideNameOptions.length > 0) {
            hideNameClasses.push(constants.CLASS_HIDE_NAME_OPTIONS);
        }
        if (vm.hideNameSelected && vm.hideNameSelected.length > 0) {
            hideNameClasses.push(constants.CLASS_HIDE_NAME_SELECTED);
        }
        vm.hideNameClasses = hideNameClasses.join(' ');
    }

    function initWatchers() {
        $scope.$watch('vm.ngModel', ngModelChangedHandler);
        $scope.$watch('vm.currencies', currencyListChangedHandler, true);
    }

    function currencyListChangedHandler(current) {
        if (!current) {
            return;
        }
        initCurrencies();
        initMappedCurrencies();
        initMappedModel();
    }

    function mappedModelChangedHandler(currentCode) {
        var newMappedModel = currencyMap[currentCode];
        var newNgModel = (newMappedModel) ? vm.extractor(newMappedModel) : undefined;
        if (!angular.equals(vm.ngModel, newNgModel)) {
            vm.ngModel = newNgModel;
            $timeout(vm.ngChange);
        }
    }

    function ngModelChangedHandler(current) {
        var newInternalModel = (current) ? vm.mapper(current) : undefined;
        if (!angular.equals(vm.mappedModel, newInternalModel)) {
            vm.mappedModel = newInternalModel;
        }
    }

    function flagCode(currency) {
        return currencyCountryMap[currency];
    }

    function defaultExtractorAndMapper(currency) {
        return currency;
    }
};

}).call(this,typeof global !== "undefined" ? global : typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
},{"./constants":4,"./currencyCountryMap":5}],3:[function(require,module,exports){
(function (global){
'use strict';

var angular = (typeof window !== "undefined" ? window['angular'] : typeof global !== "undefined" ? global['angular'] : null);
var $ = (typeof window !== "undefined" ? window['jQuery'] : typeof global !== "undefined" ? global['jQuery'] : null);

module.exports = function CurrencySelectDirective($timeout) {
    var previousTransclution = [];

    return {
        templateUrl: 'templates/currencySelect.html',
        bindToController: true,
        controller: 'CurrencySelectController',
        restrict: 'E',
        controllerAs: 'vm',
        transclude: true,
        scope: {
            currencies: '=',
            ngModel: '=',
            extractor: '=?',
            mapper: '=?',
            ngChange: '&',
            noSearch: '@',
            searchPlaceholder: '@',
            noResultsText: '@',
            noneSelectedText: '@',
            hideNameSelected: '@',
            hideNameOptions: '@'
        }, compile: function() {
            return function(scope, element, atts, controller, transcludeFn) {
                var $selectElement = $(element).find('select');
                $timeout(function() {
                    $selectElement.selectpicker();
                    moveTranscludedElement(element, transcludeFn);
                });

                $selectElement.on('change', function() {
                    var value = this.value;
                    $timeout(function() {
                        scope.vm.onChangeHandler(value);
                    });
                });

                scope.$watch('vm.mappedModel', function(current) {
                    $timeout(function() {
                        if (current && current.code) {
                            $selectElement.selectpicker('val', current.code);
                            moveTranscludedElement(element, transcludeFn);
                        } else {
                            $selectElement.selectpicker('val', '');
                            moveTranscludedElement(element, transcludeFn);
                        }
                    });
                });

                scope.$watch('vm.mappedCurrencies', function (newVal) {
                    if (!newVal) {
                        return;
                    }
                    $timeout(function () {
                        $selectElement.selectpicker('refresh');
                        moveTranscludedElement(element, transcludeFn);
                    });
                }, true);

                scope.$on('$destroy', function() {
                   $selectElement.off('change');
                });
            };
        }
    };

    function moveTranscludedElement(element, transclude) {
        var $element = $(element);
        var $dropdownMenu = $element.find('ul.dropdown-menu');

        cleanupPreviousTransclusion();

        transclude(function(clone, scope) {
            $dropdownMenu.append(clone);
            previousTransclution.push({element: clone, scope: scope});
        });
    }

    function cleanupPreviousTransclusion() {
        previousTransclution.forEach(function(t) {
            t.element.remove();
            t.scope.$destroy();
        });
        previousTransclution = [];
    }
};

}).call(this,typeof global !== "undefined" ? global : typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
},{}],4:[function(require,module,exports){
'use strict';

var constants = {};

constants.ATTR_NO_SEARCH = 'true';

constants.CLASS_HIDE_NAME_OPTIONS = 'hide-name-options';
constants.CLASS_HIDE_NAME_SELECTED = 'hide-name-selected';

constants.DEFAULT_SEARCH_PLACEHOLDER = 'Search...';
constants.DEFAULT_NO_RESULTS_PLACEHOLDER = 'No results';


module.exports = constants;
},{}],5:[function(require,module,exports){
'use strict';

module.exports = {
	EUR: 'EU',
	AUD: 'AU',
	BGN: 'BG',
	BRL: 'BR',
	CAD: 'CA',
	CHF: 'CH',
	CNY: 'CN',
	CZK: 'CZ',
	DKK: 'DK',
	GBP: 'GB',
	GEL: 'GE',
	HKD: 'HK',
	HUF: 'HU',
	INR: 'IN',
	MYR: 'MY',
	MXN: 'MX',
	NOK: 'NO',
	NZD: 'NZ',
	PLN: 'PL',
	RON: 'RO',
	SEK: 'SE',
	SGD: 'SG',
	THB: 'TH',
	NGN: 'NG',
	PKR: 'PK',
	TRY: 'TR',
	USD: 'US',
	ZAR: 'ZA',
	JPY: 'JP',
	PHP: 'PH',
	MAD: 'MA',
	COP: 'CO',
	AED: 'AE',
	IDR: 'ID',
	CLP: 'CL',
	UAH: 'UA',
	GHS: 'GH',
	RUB: 'RU',
	LKR: 'LK',
	KRW: 'KR',
	VND: 'VN',
	BDT: 'BD',
	NPR: 'NP'
};

},{}],6:[function(require,module,exports){
(function (global){
'use strict';

var angular = (typeof window !== "undefined" ? window['angular'] : typeof global !== "undefined" ? global['angular'] : null);
var CurrencySelectDirective = require('./CurrencySelectDirective');
var CurrencySelectController = require('./CurrencySelectController');
var templates = require('../build/tw-currency-select-templates');

var currencySelectModule = angular.module('tw-currency-select', [templates.name]);
currencySelectModule.controller('CurrencySelectController', ['$scope', '$timeout', CurrencySelectController]);
currencySelectModule.directive('currencySelect', ['$timeout', CurrencySelectDirective]);

}).call(this,typeof global !== "undefined" ? global : typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
},{"../build/tw-currency-select-templates":1,"./CurrencySelectController":2,"./CurrencySelectDirective":3}]},{},[6]);
