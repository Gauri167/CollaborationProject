app.controller("CommentController", function($scope) {
	
	$scope.blogComment = {
		"commentText" : "",
		"blogId" : ""
	};
	console.log("Blog add comment Controller");



	/*
	 * $scope.addBlogComment = function(blogId) { console.log("Add Comment
	 * Function");
	 * BlogService.addBlogComment(blogComment).then(function(response) {
	 * $scope.message = response.statusText; console.log('Status Text:' +
	 * response.statusText); }); }
	 */

	$scope.demo = function() {
		console.log("DEMO FUNCTION");
		alert("Demo Function");
	}
});