app.service('ChatService',function($q,$timeout,$http){
	
	var base_url="http://localhost:8084/BlogMiddle/";
	
	var service={},listener=$q.defer(),socket={client:null,stomp:null},messageIds=[];
	
	service.RECONNECT_TIMEOUT=30000;
	service.SOCKET_URL=base_url+"chat";
	service.CHAT_TOPIC="/topic/message";
	service.CHAT_BROKER="/app/chat";
	
	service.send=function(message){
		var id=Math.floor(Math.random()*1000000);
		socket.stomp.send(service.CHAT_BROKER,{priority:9},JSON.stringify({message:message,id:id}));
		alert(message);
		messageIds.push(id);
	};
	
	service.receive=function(){
		alert("recieve message function");
		$http.get(base_url+"getMessage/");
		return listener.promise;
		//return messageIds;
		
	};
	
	var reconnect=function(){
		$timeout(function(){
			initialize();
		},
		this.RECONNECT_TIMEOUT
		);
	};
	
	var getMessage=function(data){
		var message=JSON.parse(data),out={};
		out.message=message.message;
		out.time=new Date(message.time);
		
		return out;
	};
	
	var startListener=function(){
		socket.stomp.subscribe(service.CHAT_TOPIC,function(data){
		listener.notify(getMessage(data.body));	
		});
	};
	
	var initialize=function()
	{
		socket.client=new SockJS(service.SOCKET_URL);
		socket.stomp=Stomp.over(socket.client);
		socket.stomp.connect({},startListener);
		socket.stomp.onclose=reconnect;
	};
	
	initialize();
	
	return service;
});