/**
Crivia Work Flow Java Script Function Group.
**/

CriviaWorkFlowJavaScriptFunctions = {
		ev : 'EcologyVersion'
/**
{
	Desc : 'Requird Switcher | 必填状态变更'
	,Params : {
		Field ID : String
		,Is Requird : Boolean
	} 
}
 */
		,rsImg : function(){
			return '8' == '8'//this.ev
				? '<img src="/images/BacoError_wev8.gif" align="absmiddle">'
				: '<img src="/images/BacoError.gif" align="absmiddle">';
		}
		,rs : function (f,isRequird){
			var _c = this;
			if (! f){
				return;
			}
			
			var v = _c.v(f);
			var img = _c.rsImg();
			var checkerA = jQuery('input[name="inputcheck"]').val();
			var checkerB = jQuery('input[name="needcheck"]').val();
			if (isRequird) {
				checkerA=checkerA.replace(','+f,'');
				checkerB=checkerB.replace(','+f,'');
				checkerA +=','+f;
				checkerB +=','+f;
				if ('' == v){
					if ('8' == '8'//_c.ev
					&& jQuery('#'+f+'spanimg').length){
						_c.t(f+'spanimg',img,true);
					} else {
						_c.t(f,img);
					}
				}
			}else{
				checkerA=checkerA.replace(','+f,'');
				checkerB=checkerB.replace(','+f,'');
				if ('8' == '8'//_c.ev
				&& jQuery('#'+f+'spanimg').length){
					//if (_c.t(f+'spanimg',undefined,true).indexOf('src="/images/BacoError.gif"') > -1){
						_c.t(f+'spanimg','',true);
					//}
				} else if (jQuery('#'+f+'span').length){
					if (_c.t(f).indexOf('src="/images/BacoError.gif"') > -1){
						_c.t(f,'');
					}
				}
			}
			
			jQuery('input[name="inputcheck"]').val(checkerA);
			jQuery('input[name="needcheck"]').val(checkerB);
		}
		,rs2 : function (f,isRequird){
			var _c = this;
			if (! f){
				return;
			}
			
			var v = _c.v(f);
			var img = _c.rsImg();
			var checkerA = jQuery('input[name="inputcheck"]').val();
			var checkerB = jQuery('input[name="needcheck"]').val();
			if (isRequird) {
				checkerA=checkerA.replace(','+f,'');
				checkerB=checkerB.replace(','+f,'');
				checkerA +=','+f;
				checkerB +=','+f;
				if ('' == v){
					_c.t(f,img);
				}
			}else{
				checkerA=checkerA.replace(','+f,'');
				checkerB=checkerB.replace(','+f,'');
				if (_c.t(f).indexOf('src="/images/BacoError.gif"' > -1)){
					_c.t(f,'');
				}
			}
			
			jQuery('input[name="inputcheck"]').val(checkerA);
			jQuery('input[name="needcheck"]').val(checkerB);
		}


/**
{
	Desc : 'Editer Display | 编辑区显示切换(E8)'
	,Params : {
		Field ID : String
		,Is Display : Boolean
	}
}
*/
		,editerDisplay : function(id , isDisplay){
			var _c = this;
			if (jQuery('#'+ id + '_browserbtn').length){
				if (isDisplay){
		            jQuery('#'+ id + 'span_crivia').hide();
		            jQuery('#'+ id + '_browserbtn').show();
		            jQuery('#'+ id + 'span').parent().parent().show();
				} else {
		            if (! jQuery('#'+ id + 'span_crivia').length){
		                var o = jQuery('#'+ id + 'span').parent().parent().parent();
		                o.html( '<span id="'+id+'span_crivia" style="float: none;" ></span>' + o.html());
		            }
		            jQuery('#'+ id + '_browserbtn').hide();
		            jQuery('#'+ id + 'span').parent().parent().hide();
		            jQuery('#'+ id + 'span_crivia').html(_c.t(id));
		            jQuery('#'+ id + 'span_crivia').show();
				}
			} else {
				var o = jQuery('#'+ id);
				if (jQuery(o).attr('type') == 'hidden'){
					return;
				}
				var s = o.find('option:selected');
				if (isDisplay){
		            jQuery('#'+ id + 'span_crivia').hide();
					o.show();
					if (! s.length){
						if (_c.v(id)){
							_c.t(id , '');
						}
					}
				} else {
					o.hide();
					if (s.length){
						var t = jQuery('#'+ id + 'span_crivia');
			            if (! t.length){
			                var p = o.parent();
			                p.html( '<span id="'+id+'span_crivia" style="float: none;" ></span>' + p.html());
			            }
						t = jQuery('#'+ id + 'span_crivia');
			            t.html(s.text());
			            t.show();
					} else {
						if (_c.v(id)){
							_c.t(id , _c.v(id));
						}
					}
				}
			}
		}

/**
{
	Desc : 'Sub Table Each | 子表遍历'
	,Params : {
		Each Field ID : String
		,Each Function : Function
	} 
	,Return : {
		Count : Sub Table Size (Int)
		,Sum : Sub Table Data Sum (Double)
		,Values : Sub Table Values Map
		,Concat : Values Concat (Function , Param : Concater Default ',')
	}
}
 */
		,stEach : function (id , f){
			if (! id){
				return;
			}
			var _c = this;
			var sum = 0;
			var values = {};
			var fs = jQuery('input[id^="' + id + '"]');
			if (! fs.length){
				fs = jQuery('select[id^="' + id + '"]');
			}
			if (fs.length){
				for (var k = 0; k < fs.length; k++){
					var rn = jQuery(fs[k]).attr('id').substring(id.length);
					var v = _c.v(id + rn);
					sum = sum + _c.n(v, 0);
					values[rn] = v;
					if (! f){
						continue;
					}
					f(rn);
				}
			}
			return {
				count : fs.length
				,sum : sum
				,values : values
				,concat : function(concater){
					if (undefined == concater){
						concater = ',';
					}
					var m = this.values;
					var s = '';
					for (var k in m){
						if ('' != s){
							s = s + concater;
						}
						s = s + m[k];
					}
					return s;
				}
			};
		}
/**
{
	Desc : 'Value Keeper | 值变更储存'
	,Params : {
		Map Key : String
		,New Value : String
		,Map : Java Script Object
	} 
	,Return : {
		Type : Boolean
		,Desc : 'Old Value == New Value ? True : False'
	}
}
 */
		,vK : function (k , v , m){
			if (k in m){
				if (m[k] == v){
					return true;
				}
			}
			m[k] = v;
			return false;
		}
/**
{
	Desc : 'Value | 值操作'
	,Params : {
		Field ID : String
		,Input Value : String (M Get Or Set)
	} 
}
 */
		,v : function(id , v){
			var o = document.getElementById(id);
			if (! o){
				o = document.getElementById('dis'+id);
				if (! o){
					return;
				}
			}
			if (undefined == v){
				return o.value;
			} else {
				o.value = v;
				if ('hidden' == o.getAttribute('type')){
					this.t(id, v);
				}
				jQuery(o).change();
				jQuery(o).blur();
			}
		}
/**
{
	Desc : 'Text | 文本操作'
	,Params : {
		Field ID : String
		,Input Text : String (M Get Or Set)
		,Un Complete End : Boolean (M Default : False)
	} 
}
 */
		,t : function(id , t , uce){
			var end = 'span';
			if (uce){
				end = '';
			}
			var o = document.getElementById(id + end);
			if (! o){
				return;
			}
			if (undefined == t){
				return o.innerHTML;
			} else {
				o.innerHTML = t;
			}
		}
/**
{
	Desc : 'Value & Text | 值&文本赋予'
	,Params : {
		Field ID : String
		,Input Value : String
		,Input Text : String (M Default Value)
		,Text ID : String (M Default Field ID)
	} 
}
 */
		,vt : function(f , v , t , s){
			var _c = this;
			if (f == undefined || v == undefined){
				return;
			}
			if (t == undefined){
				t = v;
			}
			if (s == undefined){
				s = f;
			}
			_c.v(f, v);
			_c.t(s, t);
		}
/**
{
	Desc : 'Value & Show | 值&文本赋予'
	,Params : {
		Field ID : String
		,Input Value : String
		,Input Text : String (M Default Value)
	} 
}
 */
		,vs : function(f , v , t){
			var o = document.getElementById(f);
			if (! o){
				return;
			}
			this.v(f , v);
			if (false
			|| o.tagName == 'hidden' 
			|| (true
			&& o.tagName == 'input'
			&& o.getAttribute('type') == 'hidden')){
				if (! t){
					t = v;
				}
				this.t(f , t);
			}
		}
/**
{
	Desc : 'A Label Text | 各种链接中的文本'
	,Params : {
		Field ID : String
	} 
	,Return : Text
}
*/
		,at : function(f , ue){
			if (! f){
				return;
			}
			var e = 'span';
			if (ue){
				e = '';
			}
			return jQuery('#'+f+e).children('a').html();
		}
/**
{
	Desc : 'Add Function | 追加函数'
	,Params : {
		New Function : Function
		,Old Function Var : String (M Default : window.doSubmit)
	} 
}
 */
		,af : function(n , o){
			if (! n){
				return;
			}
			if (! o){
				o = window.doSubmit;
			}
			var f = o;
			o = function(p){
				n(f , p);
			};
		}
/**
{
	Desc : 'To Number | 数字转换'
	,Params : {
		String : String
		,Default Value : Target
		,Is Int : Boolean (M Default : False)
	} 
}
 */
		,n : function(s , dv , isInt){
			var n;
			if (isInt){
				n = parseInt(s);
			} else {
				n = parseFloat(s.replace(/\,/g,''));
			}
			return isNaN(n) ? dv : n;
		}
/**
{
	Desc : 'Value To Number | 取值数字转换'
	,Params : {
		String : Field ID
		,Default Value : Target
		,Is Int : Boolean (M Default : False)
	} 
}
 */
		,vn : function(id , dv , isInt){
			return this.n(this.v(id), dv, isInt);
		}
/**
{
	Desc : 'Listener Runner | 启动监视器'
	,Params : {
		Option : Object
		,Example : {
			t : 'Time , Number , (M Default : 100)'
			,f : [
				{
					k : 'Example Main Table Field ID , String'
					,f : function(ov , nv){
						alert(
							'Old Value : ' + ov
							+ '\n' + 'New Value : ' + nv
						);
					}
					,f2 : function(nv , ov , k){
						alert(
							'New Value : ' + nv
							+ '\n' + 'Old Value : ' + ov
							+ '\n' + 'Field Key : ' + k
						);
					}
				},{
					k : 'Example Sub Table Field ID , String'
					,d : 'Sub Table Flag , Default : False'
					,f : function(ov , nv , r){
						alert(
							'Old Value : ' + ov
							+ '\n' + 'New Value : ' + nv
							+ '\n' + 'Row Index : ' + r
						);
					}
					,f2 : function(r , nv , ov , k){
						alert(
							'Row Index : ' + r
							+ '\n' + 'New Value : ' + nv
							+ '\n' + 'Old Value : ' + ov
							+ '\n' + 'Field Key : ' + k
						);
					}
					,f3 : function(p){
						alert(
							'Row Index : ' + p.r
							+ '\n' + 'New Value : ' + p.v.n
							+ '\n' + 'Old Value : ' + p.v.o
							+ '\n' + 'Field Key : ' + p.k
						);
					}
				}
			]
		}
	} 
}
 */
		,run : function(o){
			var _c = this;
			//Init Param.
			if (! o){
				o = {};
			}
			//Run Timer When Option Is Undefined.
			if (! _c._listenerOption){
				window.setInterval(
						'CriviaWorkFlowJavaScriptFunctions._cwjsListener()'
						, _c.n(o.t, 100, true));
			}
			//Old Param.
			if (! o.k){
				//Setting Options.
				_c._listenerOption = o;
			} 
			//New Param.
			else {
				//First Setting.
				if (! _c._listenerOption || ! _c._listenerOption.f){
					//Init Param.
					_c._listenerOption = { f : [] };
				}
				//Fields Size.
				var s = _c._listenerOption.f.length;
				//Each Check.
				for (var k = 0; k < s; k++){
					//Temp Field.
					var f = _c._listenerOption.f[k];
					//Repeat Key.
					if (f.k == o.k){
						//Replace Type.
						var t = _c.n(f.t, 0, true);
						//
						switch (t) {
						//Replace.
						case -1:
							f._fs = [o.f];
							f._f2s = [o.f2];
							f._f3s = [o.f3];
							return;
						//Add To Begin.
						case 1:
							f._fs.splice(0,0,o.f);
							f._f2s.splice(0,0,o.f2);
							f._f3s.splice(0,0,o.f3);
							return;
						//Add To End.
						default:
							f._fs[f._fs.length] = o.f;
							f._f2s[f._f2s.length] = o.f2;
							f._f3s[f._f3s.length] = o.f3;
							return;
						}
					}
				}//End Of Each.
				//Init Functions.
				o._fs = [o.f];
				o._f2s = [o.f2];
				o._f3s = [o.f3];
				//Regedit Field.
				_c._listenerOption.f[s] = o;
			}
		}
		,run2 : function(k,f){
			if (!k || !f){
				return;
			}
			this.run({k:k,f3:f});
		}
		,_listenerOption : undefined
		,_cwjsListener : function(){
			var _c = this;
			//Check Option.
			if (! _c._listenerOption){
				return;
			}
			//Fields.
			var fs = _c._listenerOption.f;
			//Fields Check.
			if (! fs){
				return;
			}
			//Check Value And Run Functions.
			for (var n = 0; n < fs.length; n++){
				//Value Map.
				var m = _c._cwjsListenerValueMap;
				//Field Option.
				var f = fs[n];
				//Check Key.
				if (! f.k){
					continue;
				}
				//Function : Check & Run.
				//Param : Temp Field , Field Key , Row Number.
				var cr = function(t , fk , r){
					//Old Value.
					var ov = m[fk];
					//New Value.
					var nv = _c.v(fk);
					//Check & Value Keep.
					if (_c.vK(fk, nv, m)){
						return;
					}
					//Function : Sequnce Runner.
					//Param : Sequnce , Runner , Bak Function.
					var sr = function(s , r , b){
						//Check Runner.
						if (! r){
							return;
						}
						//Check Sequnce.
						if (s){
							//Check Sequnce.
							for (var k = 0; k < s.length; k++){
								//Run.
								r(s[k]);
							}
						} else {
							//Run Bak Function.
							r(b);
						}
					};
					//F1.
					sr(t._fs, function(f){
						//Check Function.
						if (f){
							//Run.
							f(ov , nv , r);
						}
					}, t.f);
					//F2.
					sr(t._f2s, function(f){
						//Check Function.
						if (f){
							//Run.
							f(r , nv , ov , t.k);
						}
					}, t.f2);
					//F3.
					sr(t._f3s, function(f){
						//Check Function.
						if (! f){
							return;
						}
						//F3 Param.
						var p = {
							//Key.
							k : t.k
							//Detail.
							,d : t.d
							//Values.
							,v : {
								//Old.
								o : ov
								//New.
								,n : nv
							}
							//Row Num.
							,r : r
						};
						//Run.
						f(p);
					}, t.f3);
				};
				//All Each.
				_c.stEach(f.k, function(r){
					//Check & Run.
					cr(f , f.k + r , r);
				});
			}
		}
		,_cwjsListenerValueMap : {}
/**
{
	Desc : 'Sub Table Button | 子表按钮附加'
	,Params : {
		Option : Object
		,Example : {
			stIndex : 'Sub Table Index , Int'
			,text : 'Button Text , String (M Default : 导入)' 
			,key : 'Button Key , String (M Default : T)'
			,name : 'Button Name , String (M Default : autoCreate)' 
			,onclick : 'On Click Act , String'
		}
	} 
}
 */
		,stButton : function(o){
			if (o.stIndex == undefined || ! o.onclick){
				return;
			}
			if (! o.text){
				o.text = '导入';
			}
			if (! o.key){
				o.key = 'T';
			}
			if (! o.name){
				o.name = 'autoCreate';
			}
			var bn = o.name + o.stIndex;
			var buttonBox = jQuery('#div' + o.stIndex + 'button');
			var button = 
				'<button name="' + bn + '" class="BtnFlow"'
				+ ' id="$' + bn + '$" accessKey="' + o.key + '"'
				+ ' onclick="' + o.onclick + '" type="button">'
				+ '<u>' + o.key + '</u>'
				+ '-' + o.text
				+ '</button>'
			;
			buttonBox.html(button + buttonBox.html());
		}
/**
{
	Desc : 'Open Browser | 打开对话浏览框'
	,Params : {
		Option : Object
		,Example : {
			page : 'Browser Page URL , String'
			,height : 'Browser Height , String' (M Default : 550px)
			,width : 'Browser Width , String' (M Default : 550px)
			,f : 'Browser Field , String' (M)
		}
	} 
	,Return : Page Return Value
}
 */
		,ob : function(o){
			if (! o.page){
				return;
			}
			if (! o.height){
				o.height = '550px';
			}
			if (! o.width){
				o.width = '550px';
			}
			if (true
			&& '8' == this.ev 
			&& window.Dialog 
			&& 'function' == typeof window.Dialog.open){
				Dialog.open({
					Width : o.width
					,Height : o.height
					,URL : o.page
				});
			} else {
				var r = window.showModalDialog(o.page , undefined
					,'dialogHeight:' + o.height + ';'
					+ 'dialogWidth:' + o.width + ';'
					+ 'center:yes;menubar:no');
				if (o.f && r){
					var rv = function(k){
						if (! k in r){
							return;
						}
						var s = r[k];
						return s.indexOf(",") == 0 ? s.substring(1) : s;
					};
					_C.v(o.f , rv('id'));
					_C.t(o.f , rv('name'));
				}
				return r;
			}
		}
/**
{
	Desc : 'Browser Button | 浏览按钮图标'
	,Params : {
		Field ID : String
		,Button Click Function : Function (M Override)
	} 
	,Return : Browser Button (JQuery Object)
}
 */
		,browserButton : function(id , f){
			var b = jQuery('#' + id).siblings('button:first');
			if ('function' == typeof f){
				for (var k = 0; k < b.length; k++){
					b[k].onclick = f;
				}
			}
			return b;
		}
/**
{
	Desc : 'Delete Row | 删除行'
	,Params : {
		Sub Table Index : Int
		,Row Index : Int Or String('_?') (M Delete All)
	} 
}
 */
		,deleteRow : function(stI , rI){
			var _c = this;
			var cs = document.getElementsByName('check_node_' + stI);
			if (cs.length < 1){
				return;
			}
			var n = -1;
			if (rI){
				n = 
					_c.n(rI
					, _c.n(rI.substring(1) 
						, -1
						, true), true);				
			}
			for (var k = 0; k < cs.length; k++){
				if (rI && n != _c.n(cs[k].value, -2, true)){
					continue;
				}
				cs[k].checked = 'checked';
			}
			var oldDialog = window.isdel;
			window.isdel = function(){return true;};
			eval('deleteRow' + stI + '(' + stI + ')');
			window.isdel = oldDialog;
		}
/**
{
	Desc : 'Add Row | 增加行'
	,Params : {
		Sub Table Index : Int
		,Data Action : Function (Param : Row Number)
	} 
}
 */
		,addRow : function(stI , f){
			if (undefined == stI){
				return;
			}
			var n = '_' + $G('indexnum' + stI).value;
			eval('addRow' + stI + '(' + stI + ');');
			if (f){
				f(n);
			}
		}
/**
{
	Desc : 'Top Menu | 顶部菜单附加'
	,Params : {
		Menu Action : Function
		,Text : String (M Default '菜单X')
		,Img : String (M Default 'btn_list')
	} 
}
 */
		,topMenu : function(f , text , img){
			if(! window.parent.bar){
				return;
			}
			if (! f){
				return;
			}
			var mBox = jQuery('#toolbarmenu', window.parent.document);
			var id = mBox.children('td').length+1;
			if (! text){
				text = '菜单' + id;
			}
			if (! img){
				img = 'btn_list';
			}
			var key = 'menuItemDivId' + id;
			var bar_ = window.parent.bar;
			var m = {
				id: key
				, text: text
				, iconCls: img
				, handler: f
			};
			window.parent.bar = [m];
			window.parent.setToolBarMenu();
			bar_ = bar_.push(m);
			var t = jQuery('button:contains("'+text+'")'
					, mBox).parents('table:first').onclick = f;
		}
/**
{
	Desc : 'More Menu | 右键菜单附加'
	,Params : {
		Menu Action : Function
		,Text : String (M Default '菜单X')
	} 
}
 */
		,moreMenu : function(f , text){
			if (! f){
				return;
			}
			var mBox = jQuery('div[id="menuTable"]'
				, window.frames['rightMenuIframe'].document);
			var n = mBox.children('.b-m-item').length+1;
			if (! text){
				text = '菜单' + n;
			}
			var theme = window.parent.GLOBAL_CURRENT_THEME || 'ecology7';
			var folder = window.parent.GLOBAL_SKINS_FOLDER || 'default';
			var id = 'menuItemDivId' + n;
			var m = '<div id="' + id + '"'
				+ ' class="b-m-item"'
				+ ' onmouseover="this.className=\'b-m-ifocus\'"'
				+ ' onmouseout="this.className=\'b-m-item\'"'
				+ ' unselectable="on" >'
				+ '<div class="b-m-ibody" unselectable="on" >'
				+ '<nobr unselectable="on" >'
				+ '<img align="absMiddle"'
				+ ' src="/wui/theme/'
				+ theme
				+ '/skins/'
				+ folder
				+ '/contextmenu/default/11.png"'
				+ ' width="16" >'
				+ '<button id="'
				+ id
				+ '_btn" style="width: 120px" >'
				+ text
				+ '</button>'
				+ '</nobr></div></div>';

			mBox.append(jQuery(m));
			jQuery('#rightMenuIframe')
				.height(jQuery('#rightMenuIframe').height()+24);
			jQuery('#'+id+'_btn', mBox).bind('click', f);
		}
/**
{
	Desc : 'Post Ajax | Post Ajax'
	,Params : {
		URL : String
		,Function : Result Param
	} 
}
 */
		,post : function(url , f){
			jQuery.ajax({
				type : 'post'
				, url : url
				, success : f
			});
		}
/**
{
	Desc : 'To JSON | 字符串转JSON'
	,Params : {
		JSON String : String
		,Function : Error Function
	} 
}
 */
		,json : function(s , f){
			try {
				return eval('(' + s + ')');
			} catch (e) {
				if (f){
					f(e);
				}
			}
		}
/**
{
	Desc : 'URL Parameter | 获取地址栏参数'
	,Params : {
		Parameter Key : String (Return Parameter Of Key)
		,Default Value : String (M)
	} 
	,Return : Parameters
}
 */
		,uP : function(key , dv){
			var url = window.location.href;
			if (url.indexOf('?') < 0){
				return key ? dv : {};
			}
					
			var s = url.substring(url.indexOf('?') + 1).split('&');
			var p = {};
			for (var n = 0; n < s.length; n++){
				var t = s[n].split('=');
				var k = t[0];
				var v = t[1];
				if (k in p){
					if (p[k] instanceof String){
						var ks = p[k];
						var a = [];
						a[0] = ks;
						a[1] = v;
						p[k] = a;
					} else {
						var a = p[k];
						a[a.length] = v;
						p[k] = a;
					}
				} else {
					p[k] = v;
				}
			}
			if (key){
				return p[key];
			} else {
				return p;
			}
		}
/**
{
	Desc : 'Is Read Only | 流程是否只读'
	,Return : {
		True : Is Read Only
		,False : Editing
	}
}
 */
		,isRead : function(p){
			if (! p){
				p = this.uP();
			}
			if ('reEdit' in p){
				return false;
			}
			if ('message' in p){
				return false;
			}
			if ('requestid' in p){
				return true;
			}
			return false;
		}
/**
{
	Desc : 'Map Show | 遍历集合字符'
	,Params : {
		Map : Object
		,Title : String (M)
	} 
	,Return : Map Key And Values
}
 */
		,mapShow : function(o , t){
			if (! o){
				return;
			}
			if (! t){
				t = 'Map';
			}
			var s = t + ' : \n{';
			var f = true;
			for (var k in o){
				s = s + '\n   ' + (f ? ',' : ' ') + k + ' : ' + o[k];
				f = false;
			}
			s = s + '\n}';
			return s;
		}
/**
{
	Desc : 'Only One Check | 有且只有其一'
	,Params : {
		Target Field : String
		,All Field : String[]
		,Row Index : Int Or String('_?') (M Delete All)
	} 
}
 */
		,onlyOne : function(t , mf , r){
			var _c = this;
			if (_c._onlyOneRunning){
				return;
			}
			_c._onlyOneRunning = true;
			try {
				if (! t || ! mf){
					return;
				}
				if (! r){
					r = '';
				} else if (_c.n(r, -1, true) > -1){
					r = '_' + r;
				}
				if ('' == _c.v(t+r)){
					var nv = true;
					for (var k = 0; k < mf.length; k++){
						if (mf[k] == t){
							continue;
						}
						var fk = mf[k]+r;
						if ('' != _c.v(fk)){
							if (nv){
								nv = false;
							} else {
								_c.vt(fk, '');
							}
						}
					}
					if (nv){
						for (var k = 0; k < mf.length; k++){
							var fk = mf[k]+r;
							_c.rs(fk, true);
						}
					}
				} else {
					for (var k = 0; k < mf.length; k++){
						if (mf[k] == t){
							continue;
						}
						var fk = mf[k]+r;
						_c.rs(fk, false);
						_c.vt(fk, '');
					}
				}
			} catch (e) {}
			_c._onlyOneRunning = false;
		}
		,_onlyOneRunning : false
/**
{
	Desc : 'A Label For Hrm | 人力资源链接'
	,Params : {
		Hrm Resource ID Value : String
		,Hrm Resource Last Name Text : String
	} 
	,Return : A Label HTML
}
*/
		,aHrm : function(v , t){
			return '<a onclick="pointerXY(event);"'
				+ ' href="javascript:openhrm(' + v + ');">'
				+ t + '</a>';
		}
		
		,Time : {
/**
{
	Desc : 'Time - Today | 时间操作 - 当前日期'
	,Return : yyyy-MM-dd
}
*/
			today : function(){
				var d = new Date();
				return d.getYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();
			}
		}
		
		/**
 * 吴总的方法增加
 * @param {} fieldName
 * @return {}
 */		
		,o: function(fieldName){
			var obj = jQuery("#" + fieldName);
			if(obj.length == 0){
				obj = jQuery(":input[name=" + fieldName + "]");
			}
			return obj;
		}
		
};


_C = CriviaWorkFlowJavaScriptFunctions;
_CRM = _C._cwjsListenerValueMap;



































