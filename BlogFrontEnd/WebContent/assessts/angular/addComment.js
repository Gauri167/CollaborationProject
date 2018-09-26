app.controller("CommentController", function($http,$scope,$location,$window) {
	
	$scope.blogComment = {
			"commentText" : "",
			"blogId" : ""
		};
	$scope.comments;
	console.log("Blog add comment Controller");



	
	  $scope.addBlogComment = function(blogId) {
      console.log("Add CommentFunction");
      alert(blogId);
      $http.post('http://localhost:8084/BlogMiddle/addBlogComment/'+blogId, $scope.blogComment).then(function(response) {
    	 
	  $scope.message = response.statusText; console.log('Status Text:' +
	  response.statusText);
	  
	 
	  },function(){
		  alert("false function")
		  window.location('http://localhost:8084/BlogFrontEnd/index.html#!/showblog');
	  });
      }
	  
	  $scope.getcomments=function(){
		  console.log("Comments");
		  $http.get('http://localhost:8084/BlogMiddle/blogCommenetList').then(function(response){
			  $scope.comments=response.data;
		  })
	  }
});