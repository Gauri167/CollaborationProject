var app=angular.module("myApp");
app.controller("BlogController",function($scope,$http,$location,$rootScope){
	$scope.blog={"title":'',"content":'',"username":'',"postedBy":''};
	$scope.addBlog=function(){
		console.log("Add Blog Function");
		$http.post('http://localhost:8084/BlogMiddle/addBlog',$scope.blog).then(function(response){
			$scope.message=response.statusText;
			console.log('Status Text:'+response.statusText);
		});
	}
});