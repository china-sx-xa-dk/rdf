/**
 * multiple select plugin
 * @date: 2017/09/13
 * @author: liugl@inspur.com
 */
function multselInit(divIdStr){
	$_sel=$(divIdStr);
	$_sel.each(function(){
		$(this).append("<i></i>");
	});
	$_sel.find("span.view").click(function(){
		$_t=$(this).parent("div.multsel");
		if($_t.hasClass('expand')){
			$_t.find(".selist").hide();
			$_t.find("i:first").removeClass("pop");
			$_t.removeClass('expand');
		}else {
			$_t.find(".selist").show();
			$_t.find("i:first").addClass("pop");
			$_t.addClass('expand');
		}
	});
	$_sel.find(".selist").find("a.seitem").click(function(){
		$_this=$(this);
		
		if($_this.hasClass('checked')){
			$_this.removeClass('checked');
		}else{
			$_this.addClass('checked');
		}
		multselCheck($_this.parent(".selist"),divIdStr);
	});
	
	$(document).click(function(e){
		var t = $(divIdStr)[0],target = e.target;
		if (t !== target && !$.contains(t, target)) {
			$_sel.removeClass('expand').find(".selist").hide();
			$_sel.find("i:first").removeClass("pop");
		}
	});
}
function multselCheck($obj,divIdStr){
	var names='',vals=[],valsObjArrJson=[];
	var valsObj = {};
	$obj.find('a.checked').each(function(){
		var $t=$(this);
		names+=$t.html()+' ';
		vals.push($t.attr("value"));
		
		valsObj = {"name":$t.html(),"val":$t.attr("value")};
		valsObjArrJson.push(valsObj);
	});
	if(!names){
		names='--请选择选项值--';
	}
	$obj.parent(divIdStr).find('span.view').html(names);	
	$obj.parent(divIdStr).find('input[name=multsel_val]').val(vals);
	$obj.parent(divIdStr).find('input[name=multsel_valJSON]').val(JSON.stringify(valsObjArrJson));
	
}
