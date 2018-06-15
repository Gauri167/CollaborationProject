var app=angular.module("myApp");
app.controller("UserController",function($scope,$http,$location,$rootScope){
	
	$scope.user={"email":'',"password":'',"username":'',"isOnline":'false',"phone":''};
	
	$scope.register=function(){
		console.log("registration function");
		$http.post('http://localhost:8084/BlogMiddle/registerUser',$scope.user).then(function(response){
			$scope.message=response.statusText.message;
			console.log('Status Text:'+$scope.message);
			$location.url('http://localhost:8084/BlogMiddle/validateUser')
		});
	}
});