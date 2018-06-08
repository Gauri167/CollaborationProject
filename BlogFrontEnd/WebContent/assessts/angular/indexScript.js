var app=angular.module("myApp",["ngRoute"]);
	app.config(function($routeProvider){
		$routeProvider
		.when("/",{templateUrl:"home.html"})
		.when("/contactUs",{templateUrl:"contactUs.html"})
		.when("/login",{templateUrl:"login.html"})
		.when("/register",{templateUrl:"register.html"});
	});