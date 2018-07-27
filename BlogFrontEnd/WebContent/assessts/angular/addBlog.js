app.controller("BlogController", function($scope, $http, $location, $rootScope,$log) {
	var bCtrl = this;
	$scope.blog = {
		"title" : '',
		"content" : '',
		"username" : '',
		"postedBy" : ''
	};
	
	$scope.blogData;
	$scope.thisBlog = {};
	
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

	

	/*
	 * $scope.addBlogComment = function(blogId) { console.log("Add Comment
	 * Function");
	 * BlogService.addBlogComment(blogComment).then(function(response) {
	 * $scope.message = response.statusText; console.log('Status Text:' +
	 * response.statusText); }); }
	 */

	
});