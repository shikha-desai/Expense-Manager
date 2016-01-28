var app = angular.module('expenseManager', ['ngRoute']);

	app.controller('categoryController', function(){
		
		this.categories = cat;

	});


var cat = [{"id":1, "name":"Charity"}, {"id":2, "name":"Others"}];