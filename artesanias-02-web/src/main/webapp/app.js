'use strict';

/**
 * @ngdoc overview
 * @name newappApp
 * @description
 * # newappApp
 *
 * Main module of the application.
 */
var MakeApp = angular
        .module('newApp', [
            'ngAnimate',
            'ngCookies',
            'ngResource',
            'ngRoute',
            'ngSanitize',
            'ngTouch',
            'ui.bootstrap'
        ])
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: 'src/modules/dashboard/dashboard.html',
                        controller: 'dashboardCtrl'
                    })
                    .when('/frontend', {
                        templateUrl: 'src/modules/frontend/frontend.html',
                        controller: 'frontendCtrl'
                    })
                    .when('/charts', {
                        templateUrl: 'src/modules/charts/charts/charts.html',
                        controller: 'chartsCtrl'
                    })
                    .when('/financial-charts', {
                        templateUrl: 'charts/financialCharts/financialCharts.html',
                        controller: 'financialChartsCtrl'
                    })
                    .when('/ui-animations', {
                        templateUrl: 'src/modules/uiElements/animations/animations.html',
                        controller: 'animationsCtrl'
                    })
                    .when('/ui-buttons', {
                        templateUrl: 'src/modules/uiElements/Buttons/buttons.html',
                        controller: 'buttonsCtrl'
                    })
                    .when('/ui-components', {
                        templateUrl: 'src/modules/uiElements/components/components.html',
                        controller: 'componentsCtrl'
                    })
                    .when('/ui-helperClasses', {
                        templateUrl: 'src/modules/uiElements/helperClasses/helperClasses.html',
                        controller: 'helperClassesCtrl'
                    })
                    .when('/ui-icons', {
                        templateUrl: 'src/modules/uiElements/icons/icons.html',
                        controller: 'iconsCtrl'
                    })
                    .when('/ui-modals', {
                        templateUrl: 'src/modules/uiElements/modals/modals.html',
                        controller: 'modalsCtrl'
                    })
                    .when('/ui-nestableList', {
                        templateUrl: 'src/modules/uiElements/nestableList/nestableList.html',
                        controller: 'nestableListCtrl'
                    })
                    .when('/ui-notifications', {
                        templateUrl: 'src/modules/uiElements/notifications/notifications.html',
                        controller: 'notificationsCtrl'
                    })
                    .when('/ui-portlets', {
                        templateUrl: 'src/modules/uiElements/portlets/portlets.html',
                        controller: 'portletsCtrl'
                    })
                    .when('/ui-tabs', {
                        templateUrl: 'src/modules/uiElements/Tabs/tabs.html',
                        controller: 'tabsCtrl'
                    })
                    .when('/ui-treeView', {
                        templateUrl: 'src/modules/uiElements/treeView/treeView.html',
                        controller: 'treeViewCtrl'
                    })
                    .when('/ui-typography', {
                        templateUrl: 'src/modules/uiElements/typography/typography.html',
                        controller: 'typographyCtrl'
                    })
                    .when('/email-templates', {
                        templateUrl: 'src/modules/mailbox/mailbox-templates.html',
                        controller: 'mailboxTemplatesCtrl'
                    })
                    .when('/forms-elements', {
                        templateUrl: 'src/modules/forms/elements/elements.html',
                        controller: 'elementsCtrl'
                    })
                    .when('/forms-validation', {
                        templateUrl: 'src/modules/forms/validation/validation.html',
                        controller: 'elementsCtrl'
                    })
                    .when('/forms-plugins', {
                        templateUrl: 'src/modules/forms/plugins/plugins.html',
                        controller: 'pluginsCtrl'
                    })
                    .when('/forms-wizard', {
                        templateUrl: 'src/modules/forms/wizard/wizard.html',
                        controller: 'wizardCtrl'
                    })
                    .when('/forms-sliders', {
                        templateUrl: 'src/modules/forms/sliders/sliders.html',
                        controller: 'slidersCtrl'
                    })
                    .when('/forms-editors', {
                        templateUrl: 'src/modules/forms/editors/editors.html',
                        controller: 'editorsCtrl'
                    })
                    .when('/forms-input-masks', {
                        templateUrl: 'src/modules/forms/inputMasks/inputMasks.html',
                        controller: 'inputMasksCtrl'
                    })

                    //medias
                    .when('/medias-croping', {
                        templateUrl: 'src/modules/medias/croping/croping.html',
                        controller: 'cropingCtrl'
                    })
                    .when('/medias-hover', {
                        templateUrl: 'src/modules/medias/hover/hover.html',
                        controller: 'hoverCtrl'
                    })
                    .when('/medias-sortable', {
                        templateUrl: 'src/modules/medias/sortable/sortable.html',
                        controller: 'sortableCtrl'
                    })
                    //pages
                    .when('/pages-blank', {
                        templateUrl: 'src/modules/pages/blank/blank.html',
                        controller: 'blankCtrl'
                    })
                    .when('/pages-contact', {
                        templateUrl: 'pages/contact/contact.html',
                        controller: 'contactCtrl'
                    })
                    .when('/pages-timeline', {
                        templateUrl: 'src/modules/pages/timeline/timeline.html',
                        controller: 'timelineCtrl'
                    })
                    //ecommerce
                    .when('/ecom-cart', {
                        templateUrl: 'src/modules/ecommerce/cart/cart.html',
                        controller: 'cartCtrl'
                    })
                    .when('/ecom-invoice', {
                        templateUrl: 'src/modules/ecommerce/invoice/invoice.html',
                        controller: 'invoiceCtrl'
                    })
                    .when('/ecom-pricingTable', {
                        templateUrl: 'src/modules/ecommerce/pricingTable/pricingTable.html',
                        controller: 'pricingTableCtrl'
                    })
                    //extra
                    .when('/extra-fullCalendar', {
                        templateUrl: 'src/modules/extra/fullCalendar/fullCalendar.html',
                        controller: 'fullCalendarCtrl'
                    })
                    .when('/extra-google', {
                        templateUrl: 'src/modules/extra/google/google.html',
                        controller: 'googleCtrl'
                    })
                    .when('/extra-slider', {
                        templateUrl: 'extra/slider/slider.html',
                        controller: 'sliderCtrl'
                    })
                    .when('/extra-vector', {
                        templateUrl: 'src/modules/extra/vector/vector.html',
                        controller: 'vectorCtrl'
                    })
                    .when('/extra-widgets', {
                        templateUrl: 'src/modules/extra/widgets/widgets.html',
                        controller: 'widgetsCtrl'
                    })
                    //tables
                    .when('/tables-dynamic', {
                        templateUrl: 'src/modules/tables/dynamic/dynamic.html',
                        controller: 'dynamicCtrl'
                    })
                    .when('/tables-editable', {
                        templateUrl: 'tables/editable/editable.html',
                        controller: 'editableCtrl'
                    })
                    .when('/tables-filter', {
                        templateUrl: 'src/modules/tables/filter/filter.html',
                        controller: 'filterCtrl'
                    })
                    .when('/tables-styling', {
                        templateUrl: 'src/modules/tables/styling/styling.html',
                        controller: 'stylingCtrl'
                    })
                    //user
                    .when('/user-profile', {
                        templateUrl: 'src/modules/user/profile/profile.html',
                        controller: 'profileCtrl'
                    })
                    .when('/user-sessionTimeout', {
                        templateUrl: 'src/modules/user/sessionTimeout/sessionTimeout.html',
                        controller: 'sessionTimeoutCtrl'
                    })
                    //layout
                    .when('/layout-api', {
                        templateUrl: 'src/modules/layout/api.html',
                        controller: 'apiCtrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        });


// Route State Load Spinner(used on page or content load)
MakeApp.directive('ngSpinnerLoader', ['$rootScope',
    function ($rootScope) {
        return {
            link: function (scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default
                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$routeChangeStart', function () {
                    element.removeClass('hide'); // show spinner bar
                });
                // hide the spinner bar on rounte change success(after the content loaded)
                $rootScope.$on('$routeChangeSuccess', function () {
                    setTimeout(function () {
                        element.addClass('hide'); // hide spinner bar
                    }, 500);
                    $("html, body").animate({
                        scrollTop: 0
                    }, 500);
                });
            }
        };
    }
])