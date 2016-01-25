angular.module('expenseManager',['ngRoute'])
.config(function($routeProvider) {

	$routeProvider
	.when('/', {
	templateUrl : './WebPages/Options.html'
	})
	.when('/AddExpenses', {
	templateUrl : './WebPages/AddExpenses.html'
	})
	.when('/ViewExpenses', {
	templateUrl : './WebPages/ViewExpenses.html'
	})
	.when('/Options', {
	templateUrl : './WebPages/Options.html'
	})
	.otherwise({
    redirectTo: '/'
    });

});