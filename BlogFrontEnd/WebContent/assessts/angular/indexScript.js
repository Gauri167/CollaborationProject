var app=angular.module("myApp",["ngRoute"]);
	app.config(function($routeProvider){
		$routeProvider
		.when("/",{templateUrl:"home.html"})
		.when("/contactUs",{templateUrl:"contactUs.html"})
		.when("/login",{templateUrl:"login.html"})
		.when("/register",{templateUrl:"register.html"})
		.when("/addBlog",{templateUrl:"addBlog.html"})
		.when("/approvedBlogs",{templateUrl:"approvedBlogs.html",
			                    controller:"BlogController"})
		.when("/acceptBlog",{templateUrl:"acceptBlog.html"})
		.when("/addJob",{templateUrl:"job.html"})
		.when("/activeJobs",{templateUrl:"activeJobs.html"})
		.when("/inactiveJobs",{templateUrl:"inactiveJobs.html"})
		.otherwise({templateUrl:"home.html"});
	});