app.controller("BlogController", function($scope, $http, $location, $rootScope,
		$log) {
	var bCtrl = this;
	$scope.blog = {
		"title" : '',
		"content" : '',
		"username" : '',
		"postedBy" : ''
	};
	$scope.blogComment = {
		"commentText" : "",
		"blogId" : ""
	};
	$scope.blogData;
	$scope.thisBlog = {};
	$scope.isLiked;
	console.log("Blog add Function");

	$scope.addBlog = function() {
		console.log("Add Blog Function");
		$http.post('http://localhost:8084/BlogMiddle/addBlog', $scope.blog)
				.then(function(response) {
					$scope.message = response.statusText;
					console.log('Status Text:' + response.statusText);
				});
	}

	$scope.approvedBlogList = function() {
		console.log("Get All Approved Blogs Function");
		$scope.blogData = $http({
			method : 'GET',
			url : 'http://localhost:8084/BlogMiddle/blogList'
		}).then(function(response) {
			$scope.blogData = response.data;
			$log.info(response);
		}, function(reason) {
			$scope.error = reason.data;
		});
	}

	$scope.showBlog = function(blogId) {
		console.log("Get Blog Details");
		$http.get('http://localhost:8084/BlogMiddle/showBlog/' + blogId).then(
				function(response) {
					$scope.thisBlog = response.data;
					$rootScope.ob = $scope.thisBlog;
					$log.info(response);
					$scope.message = response.statusText;
					console.log('Status Text:' + response.statusText);
					$location.url('/showblog');
				}, function(reason) {
					$scope.error = reason.data;
				});
	}

	$scope.blogLikes = function(blogId) {
		console.log("Get Blog Likes");
		$http.get('http://localhost:8084/BlogMiddle/hasUserLikedBlog' + blogId)
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
		$http.get('http://localhost:8084/BlogMiddle/updateBlogLikes' + blogId)
				.then(function(response) {
					$scope.isLiked = !$scope.isLiked;
					$scope.message = response.statusText;
					console.log('Status Text:' + response.statusText);
				});
	}

	/*
	 * $scope.addBlogComment = function(blogId) { console.log("Add Comment
	 * Function");
	 * BlogService.addBlogComment(blogComment).then(function(response) {
	 * $scope.message = response.statusText; console.log('Status Text:' +
	 * response.statusText); }); }
	 */

	
});