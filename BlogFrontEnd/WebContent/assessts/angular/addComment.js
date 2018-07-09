app.controller("CommentController", function($http,$scope) {
	
	$scope.blogComment = {
			"commentText" : "",
			"blogId" : ""
		};
	console.log("Blog add comment Controller");



	
	  $scope.addBlogComment = function(blogId) {
      console.log("Add CommentFunction");
      alert(blogId);
      $http.post('http://localhost:8084/BlogMiddle/addBlogComment/'+blogId, $scope.blogComment).then(function(response) {
	  $scope.message = response.statusText; console.log('Status Text:' +
	  response.statusText);
	  });
      }
	 

	$scope.demo = function() {
		console.log("DEMO FUNCTION");
		alert("Demo Function");
	}
});