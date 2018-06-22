app.controller("LoginController",function($scope,$http,$location,$rootScope){
	$scope.user={"email":'',"password":''};
	$scope.validate=function(){
		console.log("Validate User Function");
		$http.post('http://localhost:8084/BlogMiddle/validateUser',$scope.user).then(function(response){
			$scope.message=response.statusText.message;
			console.log('Status Text:'+$scope.message);
			$scope.user=response.data;
			$rootScope.currentUser=response.data;
			if($rootScope.currentUser.role=="A")
				{$location.url('/')
				alert($rootScope.currentUser.role);}
			if($rootScope.currentUser.role=="C")	
			{ $location.url('/addBlog')
				alert($scope.user.role);}
		},
		function()
		{
			alert("Invalid User Please try Again");
		}
		);
	}
});