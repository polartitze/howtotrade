if(!_.wordtree){_.wordtree=1;(function($){var P3=function(){$.U.call(this);$.Q(this.ta,spa)},Q3=function(a,b){$.wx.call(this);$.uu(this,this,this.Cf,this.gg,this.As,this.Cf,null,null);this.Er=null;this.Ku=[];this.th=[];this.QO=this.YL=null;this.Rf=[];this.jt=[];$.Q(this.ta,[["minFontSize",4,1],["maxFontSize",4,1],["postfix",4,1],["fontFamily",4,1],["fontStyle",16,1],["fontWeight",4,1],["fontColor",16,1],["fontOpacity",16,1],["fontDecoration",16,1]]);this.data(a,b)},tpa=function(a,b){var c=b.getParent();if(c){for(var d=0;d<c.$b();d++){var e=
c.wd(d);e!=b&&e.o("hidden",!0)}tpa(a,c)}},upa=function(a){for(var b=0;b<a.th.length;b++)a.th[b].o("hidden",!1)},vpa=function(a,b,c){if(c.length){for(var d=b.Ol(),e=!1,f=0;f<b.$b();f++)if(d[f].get("value")==c[0]){e=!0;break}e?(c.shift(),b=d[f]):(b=b.gc({value:c[0]}),c.shift());return vpa(a,b,c)}return b},wpa=function(a){a=a.split(/\s*(.+?(?:[?!]+|$|\.(?=\s+[A-Z]|$)))\s*/);return a=(0,$.Ze)(a,function(a){return!!a.length})},R3=function(a){a=a.split(/([!?,;:.&"-]+|\S*[A-Z]\.|\S*(?:[^!?,;:.\s&-]))/);
return a=(0,$.Ze)(a,function(a){return a.length&&" "!=a})},xpa=function(a,b){null!=a.ka&&($.or(a.ka,a.cd,a),a.ka.kd());a.ka=b;$.L(a.ka,a.cd,a);a.B(4100,1)},ypa=function(a,b,c){if(b){var d=b.get("fontSize");if(null==d){if((d=b.getParent())&&(1==d.$b()||c))return d=ypa(a,d,c),b.o("fontSize",d),d;c=a.g("maxFontSize");a=a.g("minFontSize");d=b.o("height")/1.5;d=$.Za(d,a,c);b.o("fontSize",d)}return d}return 0},zpa=function(a,b){if(b){var c=b.$b(),d=0;if(c)for(var e=0;e<c;e++)d+=zpa(a,b.wd(e));else d=b.get("weight")||
1;b.o("leafCount",d).o("weight",d);return d}return 0},Apa=function(a,b,c,d,e,f){if(b){b.o("connectorInXPosition",c[0]).o("connectorInYPosition",c[1]).o("connectorOutXPosition",c[0]+a.zW(b)).o("connectorOutYPosition",c[1]);var h=a.jg.bb();if(d<h){c=a.oj();var k=b.$b(),l=c.g("length"),m=c.g("offset");c=c.g("curveFactor");var p=a.g("minFontSize");if(k){if(1<k){var q=p/10;e+=q;f-=q}q=e;for(var r=!1,t,u=0;u<k;u++)if(t=b.wd(u),t.o("hidden")){r=!0;break}if((f-e)/k<1.5*p&&1<k){k="+"+b.o("leafCount")+" "+
a.g("postfix");var v=(f+e)/2;m=d;d=a.Yx(m,v,k,b.o("fontSize")||p,a.g("fontFamily"),a.g("fontWeight"));v-=d.gH()/2;d.y(v);d.tag.node=b;d.tag.f3=!0;k=d.mU();m+k>=h&&d.visible(!1);a.Ht(b.o("connectorOutXPosition"),b.o("connectorOutYPosition"),b.o("connectorOutXPosition")+20,(f+e)/2,c)}else for(r&&(d-=l+m),u=0;u<k;u++)if(t=b.wd(u),!t.o("hidden")){m=t.o("leafCount");h=b.o("leafCount");h=r?f-e:(f-e)*Math.max(1,m)/Math.max(1,h);p=q;var w=0;q=p+h;m=d;v=p+h/2;var x=[m,v];t.o("height",h).o("nodePositionX",
m).o("nodePositionY",v);a.TL(t,r);v=b.o("textHeight")/2;x[1]-=v;1!=t.$b()&&(w+=l);m=a.zW(t);v=[x[0],x[1]+v];w+=x[0]+m;Apa(a,t,v,w,p,p+h);1<b.$b()&&!r&&a.Ht(b.o("connectorOutXPosition"),b.o("connectorOutYPosition"),t.o("connectorInXPosition"),t.o("connectorInYPosition"),c)}}}}},Bpa=function(a,b){var c=new Q3(a,b);c.Fa("wordtree");return c};$.H(P3,$.U);var S3={};$.wq(S3,[[0,"curveFactor",$.ir],[0,"offset",$.fr],[0,"length",$.fr],[1,"stroke",$.Tq]]);$.S(P3,S3);P3.prototype.pa=8201;
var spa=[["curveFactor",0,8],["offset",0,8],["length",0,8],["stroke",0,8192]];P3.prototype.F=function(){var a=P3.u.F.call(this);$.Xq(this,S3,a);return a};P3.prototype.U=function(a,b){P3.u.U.call(this,a,b);$.Pq(this,S3,a,b)};$.H(Q3,$.wx);Q3.prototype.pa=$.wx.prototype.pa;Q3.prototype.ra=$.wx.prototype.ra|4112;var T3=function(){var a={};$.wq(a,[[0,"minFontSize",$.fr],[0,"maxFontSize",$.fr],[0,"fontFamily",$.Dq],[0,"fontStyle",$.cl],[0,"fontWeight",$.Aq],[0,"fontColor",$.Eq],[0,"fontOpacity",$.Fq],[0,"fontDecoration",$.bl],[0,"postfix",function(a){if($.n(a))return null===a&&(a=this.uf("postfix")),String(a)}]]);return a}();$.S(Q3,T3);$.g=Q3.prototype;$.g.La=function(){return"wordtree"};
$.g.zj=function(){return!(this.ka&&(!this.ka||this.ka.$b()))};$.g.Te=function(){return[]};$.g.ps=function(){return[this]};$.g.lV=function(a,b){return function(c){return a*(1-c)+b*c}};$.g.Hk=function(a,b){return $.su(this.data(),b)};$.g.oj=function(a){this.Kj||(this.Kj=new P3,$.W(this,"connectors",this.Kj),$.L(this.Kj,this.sia,this));return a?(this.Kj.N(a),this):this.Kj};
$.g.Sx=function(a){this.Qi||(this.Qi=new $.Hw);var b={};a&&(b.value={value:a.get("value"),type:"string"},b.weight={value:a.o("weight"),type:"number"});this.Qi.kg(a);return $.rv(this.Qi,b)};$.g.gr=function(a){if(a){"array"==$.ka(a)&&(a=a[0]);if(!$.J(a,$.fu)&&(a=this.data().Ep("value",a)[0],!a))return;this.YL=a;tpa(this,a);this.B(20,1)}};$.g.Zx=function(){upa(this);this.YL&&this.YL.getParent()&&this.gr(this.YL.getParent());return this};
$.g.sia=function(a){var b=0;$.X(a,8192)&&(b|=16);$.X(a,8)&&(b|=4);this.B(b,1)};$.g.cd=function(a){$.X(a,16)&&this.B(4100,9)};$.g.As=function(a){a.button==$.Qi&&(a=a.domTarget.tag)&&a.node&&(upa(this),this.gr(a.node))};$.g.Cf=function(a){var b=a.domTarget.tag;if(b&&b.node&&!b.f3){var c=b.node;b=this.Sa();$.Qw(b,a.clientX,a.clientY,this.Sx(c))}else this.Sa().Ic()};$.g.gg=function(){this.Sa().Ic()};
$.g.IY=function(a){if("implicit"==this.qe){if($.n(a)){if(this.QO!=a||this.u1){this.u1=!1;if(null===a||/^[\s\xa0]*$/.test(a))a=this.kw[0][0];this.QO=a;for(var b=[],c=0;c<this.kw.length;c++){var d=(0,$.za)(this.kw[c],a);-1!=d&&b.push($.Ja(this.kw[c],d))}b.length||(b[0]=[a]);a=b[0][0];$.V(this.data());this.data().$b()&&this.data().Cj(0);c={value:a};c=this.data().Cg(c,0);for(d=0;d<b.length;d++){var e=b[d];e[0]==a&&(e.shift(),vpa(this,c,e))}this.data().da(!0)}return this}return this.QO}return this};
$.g.data=function(a,b){if($.n(a)){if($.J(a,$.cu)||$.J(a,$.$t))this.ka!=a&&(this.ka&&$.or(this.ka,this.cd,this),this.ka=a,$.L(this.ka,this.cd,this),this.qe="explicit",this.B(4100,1));else if("array"==$.ka(a)&&"object"==$.ka(a[0]))this.data($.qu(a,b));else if(null===a)this.ka&&($.or(this.ka,this.cd),this.ka.kd()),this.ka=null,this.B(4100,1);else{this.qe="implicit";this.Rf=a;if("array"==$.ka(a)&&a.length)if(this.kw=[],"array"==$.ka(a[0]))for(var c=0;c<a.length;c++)this.kw.push(R3(a[c][0]));else{if("string"==
$.ka(a[0]))for(c=0;c<a.length;c++)this.kw.push(R3(a[c]))}else if("string"==$.ka(a)){c=wpa(a);for(var d=[],e=0;e<c.length;e++)d.push(R3(c[e]));this.kw=d}else this.kw=[[a.toString()]];this.ka||xpa(this,$.qu());this.u1=!0;this.IY(this.kw[0][0])}return this}return this.ka};$.g.Ph=function(){var a;0<this.jt.length?a=this.jt.pop():a=new $.Qh;return a};$.g.zW=function(a){if(a){var b=a.o("textWidth"),c=this.oj().g("offset"),d=a.getParent();d&&1<d.$b()&&(b+=c);1<a.$b()&&(b+=c);return b}return 0};
$.g.depth=function(a){if(a){var b=a.$b(),c=0,d=a.getParent();d&&(c=d.o("depth")+1);a.o("depth",c);for(c=0;c<b;c++)this.depth(a.wd(c))}};$.g.Yx=function(a,b,c,d,e,f){var h=this.Ph();h.tag||(h.tag={});h.text(c);h.y(b);h.x(a);h.fontSize(d);h.fontFamily(e);h.fontWeight(f);h.width(null);h.visible(!0);this.Ku.push(h);return h};$.g.Ht=function(a,b,c,d,e){var f=this.CC,h=this.lV(a,c),k=h(e);e=h(1-e);f.moveTo(a,b).Qk(k,b,e,d,c,d)};
$.g.TL=function(a,b){var c=0,d=a.getParent();d&&1<d.$b()&&(c+=this.oj().g("offset"));var e=a.get("value");1==a.$b()&&(e+=" ");c=a.o("nodePositionX")+c;d=a.o("nodePositionY");var f=a.get("fontFamily")||this.g("fontFamily");var h=a.get("fontWeight")||this.g("fontWeight");var k=ypa(this,a,b);f=this.Yx(c,d,e,k,f,h);f.tag={node:a,type:"node",f3:!1};h=f.gH();e=f.mU();a.o("textHeight",h).o("textWidth",e);d-=h/2;h=c+e;k=this.jg.bb();h>k&&f.width(e-(h-k)-.05*e);f.x(c).y(d)};
$.g.Oh=function(a){if(!this.nf())if(this.za||(this.za=this.Ma.Ad(),this.CC=new $.Cg,this.za.zIndex(30)),this.zj())this.za.dj();else{var b=this.Er=this.ka.wd(0);this.J(4096)&&(zpa(this,this.Er),this.depth(this.Er),this.th.length=0,this.th=this.data().Yv().kC(),this.I(4096));if(this.J(4)){this.za.dj();var c=a.left;var d=a.top;a=a.height;for(var e=this.oj(),f=e.g("offset"),h=0;h<this.Ku.length;h++){var k=this.Ku[h];this.jt.push(k)}this.Ku.length=0;this.CC.clear();this.za.suspend();k=[c+f,d+a/2];b.o("nodePositionX",
k[0]).o("nodePositionY",k[1]).o("height",a);this.TL(b,!1);c=c+f+this.zW(b)+(1<b.$b()?e.g("length"):0);Apa(this,b,k,c,d,d+a);this.CC.parent(this.za);this.I(4);for(h=0;h<this.Ku.length;h++)this.Ku[h].parent(this.za);this.za.resume();this.B(16)}if(this.J(16)){this.za.suspend();this.CC.stroke(this.oj().g("stroke"));d=this.g("fontColor");c=this.g("fontDecoration");a=this.g("fontStyle");e=this.g("fontOpacity");for(f=0;f<this.Ku.length;f++){b=this.Ku[f];if(b.tag&&b.tag.node){var l=b.tag.node;var m=l.get("fontColor")||
d;var p=l.get("fontDecoration")||c;var q=l.get("fontStyle")||a;l=l.get("fontOpacity")||e}b.color(m);b.cr(p);b.fontStyle(q);b.opacity(l);b.Dm(!1)}this.za.resume();this.I(16)}}};$.g.F=function(){var a=Q3.u.F.call(this);$.Xq(this,T3,a);"implicit"==this.qe&&(a.wordTreeRawData=JSON.stringify(this.Rf),a.word=this.QO);a.treeData=this.data().oB(["hidden"]);a.connectors=this.oj().F();return{chart:a}};
$.g.U=function(a,b){Q3.u.U.call(this,a,b);$.Pq(this,T3,a,b);"wordTreeRawData"in a?(this.data(JSON.parse(a.wordTreeRawData)),"word"in a&&this.IY(a.word),"treeData"in a&&xpa(this,$.eu(a.treeData))):"treeData"in a&&this.data($.eu(a.treeData));"connectors"in a&&this.oj().fa(!!b,a.connectors)};$.g.R=function(){Q3.u.R.call(this);$.ud(this.Kj,this.CC,this.Ku,this.jt,this.ka,this.za);this.ka=this.CC=this.Kj=null;this.Ku.length=0;this.jt.length=0;this.za=null};var U3=Q3.prototype;U3.connectors=U3.oj;
U3.word=U3.IY;U3.getType=U3.La;U3.drillTo=U3.gr;U3.drillUp=U3.Zx;U3.toCsv=U3.Hk;U3.noData=U3.Cm;$.Xp.wordtree=Bpa;$.F("anychart.wordtree",Bpa);}).call(this,$)}
