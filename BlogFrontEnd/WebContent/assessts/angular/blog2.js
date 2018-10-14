app.controller("BlogCtrl",function($scope,$http,$log){
	
	$scope.blog = {
		"title" : '',
		"content" : '',
		"username" : '',
		"postedBy" : ''
	};
	
	$scope.blogs;
	
	$scope.BlogList=function()
	{
		console.log("To be approved blog list function");
		$http.get('http://localhost:8084/BlogMiddle/tobeApprovedBlogs').then(function(response){
			$scope.blogs=response.data;
			alert($scope.blogs.length)
			if ($scope.blogs.length=0)
				$scope.message="No pending blogs"
			$log.info(response);
		});
	}
	
	$scope.approveBlog=function(blogId)
	{
		console.log("Accept Blog");
		$http.post('http://localhost:8084/BlogMiddle/acceptBlog/'+blogId).then(function(response){
			$scope.message = response.statusText;
			console.log('Status Text:' + response.statusText);
		});
	}
	
	$scope.rejectBlog=function()
	{
		console.log("Reject Blog");
	}
})