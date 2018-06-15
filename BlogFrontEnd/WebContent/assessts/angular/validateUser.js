var app=angular.module("myApp");
app.controller("LoginController",function($scope,$http,$location,$rootScope){
	$scope.user={"email":'',"password":''};
	$scope.validate=function(){
		console.log("Validate User Function");
		$http.post('http://localhost:8084/BlogMiddle/validateUser',$scope.user).then(function(response){
			$scope.message=response.statusText.message;
			console.log('Status Text:'+$scope.message);
			$location.url('http://localhost:8084/BlogMiddle/addBlog')
		});
	}
});