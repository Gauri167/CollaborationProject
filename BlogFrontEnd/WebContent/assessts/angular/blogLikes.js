app.controller("LikesController",function($http,$scope){
	
	$scope.isLiked;
	
	$scope.blogLikes = function(blogId) {
		console.log("Get Blog Likes");
		$http.get('http://localhost:8084/BlogMiddle/hasUserLikedBlog/' + blogId)
				.then(function(response) {
					if (response.data = "")
						$scope.isLiked = false;// glyphicon color is black
					else
						$scope.isLiked = true;// glyphicon color is blue
					$scope.message = response.statusText;
					console.log('Status Text:' + response.statusText);
				});
	}

	$scope.updateLikes = function(blogId) {
		console.log("Get Blog Likes");
		$http.get('http://localhost:8084/BlogMiddle/updateBlogLikes/' + blogId)
				.then(function(response) {
					$scope.isLiked = !$scope.isLiked;
					$scope.message = response.statusText;
					console.log('Status Text:' + response.statusText);
				});
	}
})