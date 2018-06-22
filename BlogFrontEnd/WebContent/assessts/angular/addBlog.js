app.controller("BlogController",function($scope,$http,$location,$rootScope){
	var bCtrl=this;
	$scope.blog={"title":'',"content":'',"username":'',"postedBy":''};
	$scope.blogData;
	console.log("Blog add Function");
	$scope.addBlog=function(){
		console.log("Add Blog Function");
		$http.post('http://localhost:8084/BlogMiddle/addBlog',$scope.blog).then(function(response){
			$scope.message=response.statusText;
			console.log('Status Text:'+response.statusText);
		});
	}
	
	function approvedBlogList(){
		console.log("Get All Approved Blogs Function");
		$http.get('http://localhost:8084/BlogMiddle/blogList').then(function(response){
			$scope.blogData=response.data;
		});
	}
	
});