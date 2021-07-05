$.fn.getNodeList=function (list,nodes){
	if($(this).data("type")!="tree")
		return null;
	if(list == null || nodes ==null)
		return list;
	for(var i=0;i<nodes.length;i++){
		list.push(nodes[i]);
		$(this).getNodeList(list,nodes[i].nodes);
	}
	return list;
}
$.fn.checkAllNode=function(silent){
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('checkAll', { silent: silent });
};
$.fn.getList=function(nodeId){
	var node = $(this).getNode(nodeId);
	if(!node )
		return null;
	var nodes = node.nodes;
	var list = [node];
	return $(this).getNodeList(list,nodes);
}
$.fn.checkChildrenNode=function(nodeId,silent){
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	
	var node = $(this).getNode(nodeId);
	if(!node || node.length)
		return $(this);
	var method =	node.state.checked?"checkNode" : "uncheckNode" ;
	var nodes = node.nodes;
	
	for(var i=0;nodes && i<nodes.length;i++){

		var child = nodes[i];
		if(!child || child.length)
			continue;
		$(this).treeview(method,child.nodeId, { silent: silent });
		$(this).checkChildrenNode(child.nodeId,silent);
	}
	return $(this);
};

$.fn.checkNode=function(nodeId,silent){
	if($(this).data("type")!="tree")
		return;
	if(nodeId == undefined)
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('checkNode',nodeId, { silent: silent });
};
$.fn.clearSearchNode=function(){
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('clearSearch');
};
$.fn.collapseAllNode=function(silent){
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('collapseAll', { silent: silent });
};
$.fn.disableAllNode=function(silent){
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('disableAll', { silent: silent });
};
$.fn.disableNode=function(nodeId,silent){
	if($(this).data("type")!="tree")
		return;
	if(nodeId == undefined)
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('disableNode',nodeId, { silent: silent });
};
$.fn.enableAllNode=function(silent){
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('enableAll', { silent: silent });
};
$.fn.enableNode=function(nodeId,silent){
	if($(this).data("type")!="tree")
		return;
	if(nodeId == undefined)
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('enableNode',nodeId, { silent: silent });
};
$.fn.expandAllNode=function(silent){
	if($(this).data("type")!="tree")
		return;
	
	if(silent!=false)
		silent = true;
	return $(this).treeview('expandAll', { silent: silent });
};
$.fn.expandNode=function(nodeId,silent){
	
	if($(this).data("type")!="tree")
		return;
	if(nodeId == 0)
		nodeId = nodeId.toString();
	if(nodeId == undefined)
		return;
	
	var node = $(this).getNode(nodeId);
	if(!node || node.length)
		return 
	if(silent!=false)
		silent = true;
	return $(this).treeview('expandNode',node.nodeId, { silent: silent });
};
$.fn.getCollapsedNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	$(this).treeview('getCollapsed',nodeId);
};
$.fn.getDisabledNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('getCollapsed',nodeId);
};
$.fn.getEnabledNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	return  $(this).treeview('getEnabled',nodeId);
};
$.fn.getExpandedNode=function(nodeId){
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('getExpanded',nodeId);
};
$.fn.getNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return null;
	return  $(this).treeview('getNode',nodeId);
};
$.fn.getParentNode=function(nodeId){
	if(nodeId == undefined)
		return null;
	if($(this).data("type")!="tree")
		return null;
	return $(this).treeview('getParent',nodeId);
};
$.fn.getSelectedNode=function(nodeId){
	if($(this).data("type")!="tree")
		return null;
	return $(this).treeview('getSelected',nodeId);
};
$.fn.getFirstSelectedNode=function(nodeId){
	if($(this).data("type")!="tree")
		return null;
	var list = $(this).treeview('getSelected',nodeId);
	if(!list || !list.length)
		return null;
	return list[0];
};
$.fn.getSiblingsNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('getSiblings',nodeId);
};
$.fn.getUnselectedNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('getUnselected',nodeId);
};
$.fn.removeNode=function(nodeId){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('remove',nodeId);
};
$.fn.revealNode=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('revealNode',nodeId, { silent: silent });
};
$.fn.searchNode=function(pattern,ignoreCase,exactMatch,revealResults){
	
	if($(this).data("type")!="tree")
		return;
	if(ignoreCase!=false)
		ignoreCase = true;
	if(exactMatch!=true)
		exactMatch = false;
	if(revealResults!=false)
		revealResults = true;
	return $(this).treeview('search',pattern, {
	  ignoreCase: ignoreCase,     // case insensitive
	  exactMatch: exactMatch,    // like or equals
	  revealResults: revealResults,  // reveal matching nodes
	});
};
$.fn.selectNode=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	var ret = $(this).treeview('selectNode',nodeId, { silent: silent });
	$(this).revealNode(nodeId,silent);
	return ret;
};
$.fn.toggleNodeChecked=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('toggleNodeChecked',nodeId, { silent: silent });
};
$.fn.toggleNodeDisabled=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('toggleNodeDisabled',nodeId, { silent: silent });
};
$.fn.toggleNodeExpanded=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('toggleNodeExpanded',nodeId, { silent: silent });
};
$.fn.toggleNodeSelected=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('toggleNodeSelected',nodeId, { silent: silent });
};
$.fn.uncheckAllNode=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('uncheckAll',nodeId, { silent: silent });
};
$.fn.uncheckNode=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('uncheckNode',nodeId, { silent: silent });
};
$.fn.unselectNode=function(nodeId,silent){
	if(nodeId == undefined)
		return;
	if($(this).data("type")!="tree")
		return;
	if(silent!=false)
		silent = true;
	return $(this).treeview('unselectNode',nodeId, { silent: silent });
};
$.fn.getTreeData=function(){
	if($(this).data("type")!="tree")
		return;
	return $(this).treeview('getTreeData');
};
$.fn.initTreeView=function(){
	var _tree_events=["nodeChecked","nodeCollapsed","nodeEnabled","nodeExpanded","loadCompleted",
	                  "nodeSelected","nodeUnchecked","nodeUnselected","searchComplete","searchCleared"];
	var data = $(this).data();
		var treeObj = $(this);
		var list = null;
		if(data.func && window[data.func] )
		{
			list = window[data.func]();
		}
		else if(data.url){
			list = Utilities.getObject(data.url,{abc:123},true);
		}
		if(list == null)
			return ;
		var option= {
						backColor : null,
						borderColor : null,
						checkedIcon : "fas fa-check-circle",
						collapseIcon : "fas fa-minus-circle",
						color : null,
						emptyIcon: null,
						enableLinks : false,
						expandIcon : "fas fa-plus-circle",
						selectedIcon:"glyphicon glyphicon-file",
						highlightSearchResults : true,
						highlightSelected : true,
						levels : 2,
						multiSelect :false,
						nodeIcon : "glyphicon glyphicon-stop",
						onhoverColor: "#F5F5F5",
						searchResultBackColor : null,
						searchResultColor : "#D9534F",
						//selectedBackColor : "#428bca",
						selectedColor : "#FFFFFF", 
						showBorder : true,
						showCheckbox : false,
						showIcon : true,
						showTags : true,
						uncheckedIcon : "glyphicon glyphicon-unchecked"
						};
		option = $.extend(option,data);
		option.data= list;
		$(this).treeview(option);
		for(var i=0;i<_tree_events.length;i++){
			var evtName = _tree_events[i];
			createTreeEvent($(this),evtName);
		}
		loadTreeExpand($(this));
		$(this).css("overflow-x","auto");
		treeObj.trigger( "loadCompleted" );

};
$.fn.refreshTree=function(noSelect){
	if($(this).data("type")!="tree")
		return;
	saveTreeExpend($(this));
	var arr = $("#tree").getSelectedNode();
	var _tree_events=["nodeChecked","nodeCollapsed","nodeEnabled","nodeExpanded",
	                  "nodeSelected","nodeUnchecked","nodeUnselected","searchComplete","searchCleared"];
	var options =$(this).treeview("options");
	var list = null;
	if(options.func && window[options.func] )
	{
		list = window[options.func]();
	}
	else if(options.url){
		list = Utilities.getObject(options.url,{},true);
	}
	else
		list=[];
	options.data=list;
	$(this).treeview(options);
		for(var i=0;i<_tree_events.length;i++){
			var evtName = _tree_events[i];
			createTreeEvent($(this),evtName);
		}
	loadTreeExpand($(this));
	if(!noSelect){
		for(var i=0;arr && i<arr.length;i++){
			$(this).selectNode(arr[i].nodeId);
		}
	}
	
}
$(document).ready(function(){
	
	$("[data-type=tree]").each(function(){
		
		$(this).initTreeView();
	});
	
});
function createTreeEvent(treeObj,evtName){
	treeObj.on(evtName, function(event, data) {
				var func = treeObj.data(evtName);
				if(evtName == "searchComplete"){
					loadTreeExpand(treeObj);
				}
				if(func && window[func] && typeof window[func]=="function")
					window[func](event,data);
			});
}
function saveTreeExpend(treeObj){
	if(!treeObj||!treeObj.getExpandedNode )
		return;
	var prg = _PROGRAM_ID? _PROGRAM_ID+"" : "";
	var id = treeObj.attr("id");
	if(!id)
		return;
	var cName =prg+id;
	var arr = treeObj.getExpandedNode();
	var list = [];
	for(var i=0;arr && i<arr.length;i++){
		list.push(arr[i].nodeId);
	}
	Utilities.setCookie(cName,Utilities.toJSON(list),7);
	
}
function loadTreeExpand(treeObj){
	if(!treeObj)
		return;
	try{	treeObj.expandNode(0);}catch(e){}

	var prg = _PROGRAM_ID? _PROGRAM_ID+"" : "";
	var id = treeObj.attr("id");
	if(!id)
		return;
	var cName =prg+id;
	var json = Utilities.getCookie(cName);
	if(!json)
		return;
	var arr= Utilities.parseJSON( Utilities.getCookie(cName));
	for(var i=0;arr && i<arr.length;i++){
		try{treeObj.expandNode(arr[i]);}catch(e){}
		
	}
}
$(window).on('beforeunload', function(){
  $("[data-type=tree]").each(function(){
	  saveTreeExpend($(this));
	  
  });
});