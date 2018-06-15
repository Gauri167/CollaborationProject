var app=angular.module("myApp",["ngRoute"]);
	app.config(function($routeProvider){
		$routeProvider
		.when("/",{templateUrl:"home.html"})
		.when("/contactUs",{templateUrl:"contactUs.html"})
		.when("/login",{templateUrl:"login.html"})
		.when("/register",{templateUrl:"register.html"})
		.when("/addBlog",{templateUrl:"addBlog.html"})
		.when("/acceptBlog",{templateUrl:"acceptBlog.html"})
		.when("/addJob",{templateUrl:"job.html"});
	});