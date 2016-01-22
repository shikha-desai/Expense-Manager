angular.module('expenseManager')
.config(function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise('/');

	$stateProvider
	.state('AddExpenses', {
		url: '/AddExpenses',
		templateURL: 'WebPages/AddExpenses.html'
	})
	.state('ViewExpenses', {
		url: '/ViewExpenses',
		templateURL: 'WebPages/ViewExpenses.html'
	})
	.state('Options', {
		url: '/Options',
		templateURL: 'WebPages/Options.html'
	})
	.state('Root', {
		url: '/',
		templateURL:'index.html'
	})

});