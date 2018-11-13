define("bui/module",["jquery","bui/common"],function(e,t,n){function i(){return"module"+o++}var s=e("jquery"),u=e("bui/common"),o=1,a=function(e){a.superclass.constructor.call(this,e),this.get("id")||this.set("id",i()),this.get("autoInit")&&this.init(),a.Manager.add(this)};a.ATTRS={id:{},autoInit:{value:!1},hasInit:{value:!1},parent:{},destroyed:{value:!1},modules:{value:{},shared:!1},events:{value:["change"]}},u.extend(a,u.Base),u.augment(a,{set:function(e,t){this.setInternal?this.setInternal(e,t):a.superclass.set.call(this,e,t)},init:function(){return this.get("hasInit")||(this._initData(),this._initDom(),this._initModules(),this._initEvent(),this.set("hasInit",!0)),this},fire:function(e,t,n){n=void 0==n?!0:n;var i=s.makeArray(arguments),u=this,o=t;t&&t.module&&(u=t.module,o=t.event),a.superclass.fire.apply(this,i);var d=this.get("parent");d&&d.fire("change",{module:u,eventType:e,event:o},!1),n&&a.Manager.fire("change",{module:this,eventType:e,event:t})},addModule:function(e){e.set("parent",this),this.get("modules")[e.get("id")]=e},removeById:function(e){this.removeModule(this.getModule(e))},removeModule:function(e){delete this.get("modules")[e.get("id")]},getModule:function(e){return this.get("modules")[e]},eachModule:function(e){u.each(this.getModules(),e)},getModules:function(){return this.get("modules")},_initData:function(){},_initDom:function(){},_initModules:function(){},_initEvent:function(){},_destroy:function(){var e=this,t=e.get("modules");u.each(t,function(e){e.destroy()}),e.set("modules",null),e.detach()},destroy:function(){this.get("destroyed")||(a.Manager.remove(this),this._destroy(),this.set("destroyed",!0))}});var d={},r=function(){};u.extend(r,u.Base),u.augment(r,{add:function(e){var t=e.get("id");d[t]=e},remove:function(e){var t=e.get("id");delete d[t]},getModule:function(e){return d[e]}}),a.Manager=new r,n.exports=a});