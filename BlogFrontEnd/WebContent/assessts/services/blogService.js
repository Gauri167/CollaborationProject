app.factory("BlogService",function($http){
	
	var blog={}
	var blogComment={}
	var BASE_URL='http://localhost:8084/BlogMiddle'
		
		blog.addBlogComment=function(){
		return $http.post(BASE_URL+'/addBlogComment',blogComment)
	}
	
	return blog;
})