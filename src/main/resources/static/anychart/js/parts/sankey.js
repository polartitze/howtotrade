if(!_.sankey){_.sankey=1;(function($){var fT=function(a,b){$.U.call(this);this.xa=a;this.qe=b;this.fb=null;var c={};$.Q(c,[["fill",0,8192],["stroke",0,8192],["labels",0,0]]);this.ca=new $.oy(this,c,$.hm);$.W(this,"normal",this.ca);this.ca.sa("labelsFactoryConstructor",$.py);this.ca.sa("labelsAfterInitCallback",$.uy);this.ya=new $.oy(this,c,$.Ao);$.W(this,"hovered",this.ya);this.ya.sa("labelsFactoryConstructor",$.qy);this.ya.sa("labelsAfterInitCallback",function(a){a.I(4294967295)})},gT=function(a,b){$.wx.call(this);this.Fa("sankey");
$.uu(this,this,this.Cf,this.gg,null,this.Cf,null,null);$.Q(this.ta,Kfa);this.data(a||null,b);this.DR=(0,$.pa)(this.DR,this);this.AR=(0,$.pa)(this.AR,this)},Lfa=function(a,b){for(var c=b.$D,d=0;d<c.length;d++){var e=c[d];b.level>=e.level&&(e.level=b.level+1,Lfa(a,e));e.level>a.G&&(a.G=e.level)}},Mfa=function(a,b){return b?(a.hf[b]||(a.hf[b]={type:hT,name:b,level:0,gN:0,gO:0,AG:0,EH:[],$D:[],Q2:[],t4:[],d1:[],Usa:[],dta:[],Ay:[],Py:[],iS:!1}),a.hf[b]):null},Ofa=function(a,b){var c=b.type,d;if(c==hT){var e=
b.name;a.isConflict={value:b.iS,type:"string"};var f=[];for(d=0;d<b.EH.length;d++){var h=b.EH[d];var k=$.wa(h.$D,function(a){return a==b});f.push({name:h.name,value:h.t4[k]})}a.income={value:f,type:""};f=[];for(d=0;d<b.$D.length;d++)h=b.$D[d],k=$.wa(h.EH,function(a){return a==b}),f.push({name:h.name,value:h.Q2[k]});a.outcome={value:f,type:""};a.dropoff={value:b.AG,type:"number"}}else e=c==iT?b.from.name+" -> "+b.ye.name:b.from.name+" dropoff";a.type={value:Nfa[c],type:"string"};a.name={value:e,type:"string"};
a.value={value:b.weight,type:"number"}},Pfa=function(a,b,c){c=c?a.node().kb().labels():a.node().Pa().labels();a=a.node().Pa().labels();c=c.g("position");a=a.g("position");a=$.Fk(c||a,"center");var d=c=0,e=(b.Hj+b.ki)/2,f=(b.ze+b.Pe)/2;switch(a){case "left-top":c=b.Hj;d=b.ze;break;case "left-center":c=b.Hj;d=f;break;case "left-bottom":c=b.Hj;d=b.Pe;break;case "center-top":c=e;d=b.ze;break;case "center":c=e;d=f;break;case "center-bottom":c=e;d=b.Pe;break;case "right-top":c=b.ki;d=b.ze;break;case "right-center":c=
b.ki;d=f;break;case "right-bottom":c=b.ki,d=b.Pe}return{value:{x:c,y:d}}},Qfa=function(a,b,c,d,e,f){for(var h,k,l=0;l<b.length;l++)h=b[l],k=h.path,jT(a,a.b,k.tag,k,d),h.label.nk(c),h.label.ic({value:e(h,f)}),a.je(a.b,h,d)},jT=function(a,b,c,d,e){c=a.He(c);a=b.Rm(e,c);b=b.fk(e,c);d.fill(a);d.stroke(b)},Rfa=function(a,b,c,d,e,f){var h=b.tag;jT(a,a.f,h,b,c);b=h.element;Qfa(a,b.Ay,d,c,f,"leftTop");Qfa(a,b.Py,e,c,f,"rightTop");b.label.ic(Pfa(a,b,c));a.je(a.f,b,c)},Sfa=function(a,b,c){b=b.tag;var d=b.element;
jT(a,a.b,b,d.path,c);d.label.nk("center-bottom");d.label.ic({value:a.uY(d)});jT(a,a.f,d.from.path.tag,d.from.path,c);jT(a,a.f,d.ye.path.tag,d.ye.path,c);a.je(a.b,d,c)},Tfa=function(a,b,c){var d=b.tag;jT(a,a.j,d,b,c);a.je(a.j,d.element,c)},kT=function(a){return(a.ze+a.Pe)/2},Ufa=function(a,b){for(var c=0;c<a.i.length;c++)for(var d=a.i[c].hf,e=0;e<d.length;e++){var f=d[e];if(f.Ay.length){var h=(0,$.Kg)(f.Ay,function(a,b){return a+kT(b.from)*b.weight},0,a),k=(0,$.Kg)(f.Ay,function(a,b){return a+b.weight},
0,a);h=(h/k-kT(f))*b;f.ze+=h;f.Pe+=h}}},Vfa=function(a,b){for(var c=a.i.slice(),d=0;d<c.length;d++)for(var e=a.i[d].hf,f=0;f<e.length;f++){var h=e[f];if(h.Py.length){var k=(0,$.Kg)(h.Py,function(a,b){return a+kT(b.ye)*b.weight},0,a),l=(0,$.Kg)(h.Py,function(a,b){return a+b.weight},0,a);k=(k/l-kT(h))*b;h.ze+=k;h.Pe+=k}}},lT=function(a,b){for(var c=0;c<a.i.length;c++){var d=a.i[c].hf.slice(),e=b.top,f=e+b.height,h=d.length,k,l=a.g("nodePadding");d.sort(a.CR);for(k=0;k<h;++k){var m=d[k];e-=m.ze;0<e&&
(m.ze+=e,m.Pe+=e);e=m.Pe+l}e=e-l-f;if(0<e)for(m.ze=m.ze-e,m.Pe=m.Pe-e,e=m.ze,k=h-2;0<=k;--k)m=d[k],e=m.Pe+l-e,0<e&&(m.ze-=e,m.Pe-=e),e=m.ze}},mT=function(a,b,c){var d;for(d=0;d<b.length;d++){var e=b[d];jT(a,c,e.tag,e,$.hm)}},Wfa=function(a,b){return new gT(a,b)};$.H(fT,$.U);$.Kq(fT,["fill","stroke","labels"],"normal");$.g=fT.prototype;$.g.pa=$.U.prototype.pa|28672;$.g.La=function(){return this.qe};$.g.Hg=function(){$.W(this,"normal",this.ca);$.W(this,"hovered",this.ya)};
$.g.Sa=function(a){this.fb||(this.fb=new $.yw(0),this.fb.df(),$.W(this,"tooltip",this.fb),this.fb.parent(this.xa.Sa()),this.fb.xa(this.xa));return $.n(a)?(this.fb.N(a),this):this.fb};$.g.xp=function(){this.va(16384)};$.g.ae=function(){this.va(4096)};$.g.nI=function(){this.ca.labels().I(4294967295);this.ya.labels().I(4294967295)};$.g.Pa=function(a){return $.n(a)?(this.ca.N(a),this):this.ca};$.g.kb=function(a){return $.n(a)?(this.ya.N(a),this):this.ya};$.g.Rm=function(a,b){return this.Cp("fill",a,b)};
$.g.fk=function(a,b){return this.Cp("stroke",a,b)};$.g.Cp=function(a,b,c){a=(b?this.ya:this.ca).g(a)||this.ca.g(a);$.E(a)&&(a=a.call(c,c));return a};$.g.F=function(){var a=fT.u.F.call(this);a.tooltip=this.Sa().F();a.normal=this.ca.F();a.hovered=this.ya.F();return a};$.g.U=function(a,b){fT.u.U.call(this,a,b);"tooltip"in a&&this.Sa().fa(!!b,a.tooltip);this.ca.fa(!!b,a);this.ca.fa(!!b,a.normal);this.ya.fa(!!b,a.hovered)};$.g.R=function(){$.ud(this.fb,this.ca,this.ya);fT.u.R.call(this)};var nT=fT.prototype;
nT.tooltip=nT.Sa;nT.normal=nT.Pa;nT.hovered=nT.kb;$.H(gT,$.wx);$.Lz(gT,"sankey",["appearance","data","flowlabels","nodelabels"]);gT.prototype.pa=$.wx.prototype.pa;var oT={};$.wq(oT,[[0,"nodeWidth",$.fr],[0,"nodePadding",$.Fq],[0,"curveFactor",$.ir]]);$.S(gT,oT);var Kfa=[["nodeWidth",4,1],["nodePadding",4,1],["curveFactor",4,1]];$.g=gT.prototype;
$.g.data=function(a,b){if($.n(a)){if(a){var c=a.title||a.caption;c&&this.title(c);a.rows&&(a=a.rows)}this.Rf!==a&&(this.Rf=a,$.pd(this.ka),$.pd(this.Xc),this.rd=null,$.J(a,$.Gr)?this.ka=a.Si():this.ka=$.J(a,$.Qr)?a.Wd():(this.Xc=new $.Qr($.A(a)||$.z(a)?a:null,b)).Wd(),$.L(this.ka,this.cd,this),this.B(256),$.rr(this,"sankey","data",1));return this}return this.ka};$.g.cd=function(){this.B(256);$.rr(this,"sankey","data",1)};$.g.Xf=function(){return this.ka.$()};$.g.Ac=function(){return this.rd=this.ka.$()};
$.g.$=function(){return this.rd||(this.rd=this.ka.$())};$.g.La=function(){return"sankey"};
$.g.nb=function(){if($.vr(this,"sankey","data")){this.hf={};this.D={};this.G=-1;for(var a=this.$().reset();a.advance();){var b=String(a.get("from"));var c=a.get("to");c=null===c||$.C(c)?null:String(c);var d=$.N(a.get("weight"));var e=!b.length;var f=null!==c&&!c.length;$.ea(d)&&0<d&&!e&&!f&&(b=Mfa(this,b),e=Mfa(this,c),c=b,b=e,e=this.$().la(),this.D[e]={type:b?iT:Xfa,Lh:e,from:c,ye:b,weight:d},c.gO+=d,b?(c.Py.push(this.D[e]),c.t4.push(d),c.$D.push(b),b.gN+=d,b.Q2.push(d),b.EH.push(c),b.Ay.push(this.D[e]),
c.level>=b.level&&(b.level=c.level+1,Lfa(this,b)),b.level>this.G&&(this.G=b.level)):(c.AG+=d,c.d1.push(d)))}this.i=[];this.Ba=!0;for(var h in this.hf)d=this.hf[h],d.weight=Math.max(d.gN,d.gO),a=d.$D.length+d.d1.length,this.Ba&&!a&&(d.level=this.G),d.EH.length&&a&&(d.iS=d.gN!=d.gO),a=d.level,this.i[a]||(this.i[a]={hf:[],weight:0,top:window.NaN}),a=this.i[a],a.hf.push(d),a.weight+=d.weight;for(d=h=0;d<this.i.length;d++)for(a=this.i[d],c=0;c<a.hf.length;c++)a.hf[c].id=h++;this.B(4);$.tr(this,"sankey",
"data")}};$.g.zj=function(){return!this.$().Jb()};$.g.Te=function(){return[]};$.g.xp=function(){this.Sa().W()};$.g.dG=function(a,b){if(!this.aa||b)this.aa=new $.Hw;var c={};Ofa(c,a);if(a.type!=hT){var d=this.$();d.select(a.Lh);this.aa.kg(d)}else this.aa.kg(null);return $.rv(this.aa,c)};$.g.Sx=function(a){this.Qi||(this.Qi=new $.Hw);var b={};a=a.element;Ofa(b,a);if(a.type!=hT){var c=this.$();c.select(a.Lh);this.Qi.kg(c)}else this.Qi.kg(null);return $.rv(this.Qi,b)};
$.g.yga=function(a,b){return{x:a[b].x,y:a[b].y}};$.g.uY=function(a){return{x:(a.left+a.right)/2,y:a.topCenter}};$.g.Cf=function(a){var b=a.domTarget,c=b.tag;if(c){var d=c.element.type;d==hT?(d=this.f.Sa(),Rfa(this,b,$.Ao,"left-bottom","right-bottom",this.yga)):d==iT?(d=this.b.Sa(),Sfa(this,b,$.Ao)):(d=this.j.Sa(),Tfa(this,b,$.Ao));$.Qw(d,a.clientX,a.clientY,this.Sx(c))}else this.Sa().Ic()};
$.g.gg=function(a){a=a.domTarget;var b=a.tag;this.Sa().Ic();b&&(b=b.element.type,b==hT?Rfa(this,a,$.hm,"center-bottom","center-bottom",this.uY):b==iT?Sfa(this,a,$.hm):Tfa(this,a,$.hm))};$.g.hT=function(a){var b=[];$.X(a,8192)&&b.push("appearance");$.X(a,4096)&&(a=a.target.La()==hT,b.push(a?"nodelabels":"flowlabels"));$.sr(this,"sankey",b,1)};$.g.ZL=function(a){this.j||(this.j=new fT(this,Xfa),$.W(this,"dropoff",this.j),this.j.Hg(),$.L(this.j,this.hT,this));return $.n(a)?(this.j.N(a),this):this.j};
$.g.oM=function(a){this.b||(this.b=new fT(this,iT),$.W(this,"flow",this.b),this.b.Hg(),$.L(this.b,this.hT,this));return $.n(a)?(this.b.N(a),this):this.b};$.g.node=function(a){this.f||(this.f=new fT(this,hT),$.W(this,"node",this.f),this.f.Hg(),$.L(this.f,this.hT,this));return $.n(a)?(this.f.N(a),this):this.f};
$.g.ac=function(a){if($.J(a,$.Ps))return this.Ec($.Ps,a),this;if($.J(a,$.Ms))return this.Ec($.Ms,a),this;$.C(a)&&"range"==a.type?this.Ec($.Ps):($.C(a)||null==this.Ea)&&this.Ec($.Ms);return $.n(a)?(this.Ea.N(a),this):this.Ea};$.g.Ec=function(a,b){if($.J(this.Ea,a))b&&this.Ea.N(b);else{var c=!!this.Ea;$.pd(this.Ea);this.Ea=new a;$.W(this,"palette",this.Ea);this.Ea.Dp();b&&this.Ea.N(b);$.L(this.Ea,this.Jf,this);c&&$.rr(this,"sankey","appearance",1)}};
$.g.Jf=function(a){$.X(a,2)&&$.rr(this,"sankey","appearance",1)};$.g.He=function(a){var b=a.element;a=a.element.type;var c=this.ac();return a==hT?{id:b.id,name:b.name,sourceColor:c.mc(b.id),conflict:b.iS}:a==iT?{from:b.from.name,to:b.ye.name,sourceColor:c.mc(b.from.id)}:{from:b.from.name,sourceColor:c.mc(b.from.id)}};var hT=0,iT=1,Xfa=2,Nfa={0:"node",1:"flow",2:"dropoff"};$.g=gT.prototype;$.g.AR=function(a,b){return this.CR(a.from,b.from)};$.g.DR=function(a,b){return this.CR(a.ye,b.ye)};
$.g.CR=function(a,b){return a.ze-b.ze};$.g.lV=function(a,b){return function(c){return a*(1-c)+b*c}};
$.g.Oh=function(a){if(!this.nf()){this.nb();this.za||(this.za=this.Ma.Ad(),this.za.zIndex(30),this.node(),this.oM(),this.ZL());if(this.J(4)){this.za.dj();this.f.labels().B(2);this.b.labels().B(2);this.j.labels().B(2);this.K=[];this.ba=[];this.P=[];this.ea=[];var b=this.g("nodePadding"),c=this.g("nodeWidth");if(this.i.length){var d=this.i.length;var e=a.width/d;c=$.M(c,e);e=.3*c}else c=e=d=0;var f,h=[];for(f=0;f<this.i.length;f++){var k=this.i[f];var l=k.hf;var m=l[l.length-1].AG?e:0;l=(a.height-m-
b*(l.length-1))/k.weight;h.push(l)}this.ma=Math.min.apply(null,h);b=(a.width-c)/(d-1);for(f=0;f<this.i.length;f++)for(k=this.i[f],l=k.hf,k=0;k<l.length;k++)d=l[k],d.Hj=a.left+d.level*b,d.ki=d.Hj+c,d.ze=a.top+k,d.Pe=d.ze+d.weight*this.ma;for(var p in this.D)l=this.D[p],l.height=l.weight*this.ma;lT(this,a);l=1;for(f=32;0<f;--f)l*=.99,Vfa(this,l),lT(this,a),Ufa(this,l),lT(this,a);for(v in this.hf)d=this.hf[v],d.Py.sort(this.DR),d.Ay.sort(this.AR);for(v in this.hf){d=this.hf[v];var q=b=d.ze;for(f=0;f<
d.Py.length;f++)l=d.Py[f],l.ze=q+l.height/2,q+=l.height;for(f=0;f<d.Ay.length;f++)l=d.Ay[f],l.Pe=b+l.height/2,b+=l.height}for(v in this.hf)d=this.hf[v],k=this.za.path(),k.zIndex(3),this.ba.push(k),k.tag={element:d},d.path=k,d.Hj=$.O($.Wm(d.Hj,4),1),d.ze=$.O($.Wm(d.ze,4),1),d.ki=$.O($.Wm(d.ki,4),1),d.Pe=$.O($.Wm(d.Pe,4),1),k.moveTo(d.Hj,d.ze).lineTo(d.ki,d.ze).lineTo(d.ki,d.Pe).lineTo(d.Hj,d.Pe).lineTo(d.Hj,d.ze).close();d=this.g("curveFactor");for(p in this.D)if(l=this.D[p],l.ye){k=this.za.path();
k.zIndex(1);this.P.push(k);k.tag={element:l};l.path=k;a=l.from.ki;f=l.ye.Hj;q=l.ze;b=l.Pe;m=this.lV(a,f);h=m(d);m=m(1-d);var r=l.height/2;l.left=a;l.right=f;l.topCenter=(q+b)/2-r;l.leftTop={x:a,y:q-r};l.rightTop={x:f,y:b-r};var t=q-r,u=b-r;q+=r;b+=r;k.moveTo(a,t).Qk(h,t,m,u,f,u).lineTo(f,b).Qk(m,b,h,q,a,q).lineTo(a,t);3>l.height&&(k=this.za.path(),k.zIndex(2),this.ea.push(k),k.fill($.cm).stroke($.cm,3),k.moveTo(a,t).Qk(h,t,m,u,f,u).lineTo(f,b).Qk(m,b,h,q,a,q).lineTo(a,t),k.tag={element:l})}else b=
l.from.AG*this.ma,m=Math.min(b,c/4),a=l.from.ki,f=a+m,h=l.from.Pe,b=h-b,k=this.za.path(),k.zIndex(1),this.K.push(k),k.tag={element:l},l.path=k,l.Lja=f,l.Mja=h,k.moveTo(a,b).arcTo(m,m,-90,90),b+m<h&&k.lineTo(f,h),k.lineTo((a+f)/2,h+e).lineTo(a,h).close();$.sr(this,"sankey",["appearance","nodelabels","flowlabels"]);this.I(4)}$.vr(this,"sankey","appearance")&&(mT(this,this.ba,this.f),mT(this,this.P,this.b),mT(this,this.K,this.j),$.tr(this,"sankey","appearance"));if($.vr(this,"sankey","nodelabels")){l=
this.f.labels();l.clear().O(this.za).zIndex(3);for(var v in this.hf)d=this.hf[v],a=d.id,c=this.dG(d,!0),e=Pfa(this,d,$.hm),d.label=l.add(c,e,a),this.je(this.f,d,$.hm);l.W();this.f.nI();$.tr(this,"sankey","nodelabels")}if($.vr(this,"sankey","flowlabels")){v=this.b.labels();f=this.j.labels();v.clear().O(this.za).zIndex(3);f.clear().O(this.za).zIndex(3);for(p in this.D)l=this.D[p],a=$.N(p),d=l.ye,c=this.dG(l,!0),d?(e={value:this.uY(l)},l.label=v.add(c,e,a),l.label.nk("center-bottom")):(e={value:{x:l.Lja,
y:l.Mja}},l.label=f.add(c,e,a),l.label.nk("left-center")),this.je(d?this.b:this.j,l,$.hm);v.W();f.W();this.b.nI();this.j.nI();$.tr(this,"sankey","flowlabels")}}};
$.g.je=function(a,b,c){var d=b.label;if(d){var e=this.$();a.La()==hT?e.reset():e.select(b.Lh);b=e.get("normal");b=$.n(b)?b.label:void 0;var f=e.get("hovered");f=$.n(f)?f.label:void 0;b=$.dp(b,e.get("label"),null);e=$.dp(f,e.get("hoverLabel"),null);e=c?e:null;f=c?a.kb().labels():null;var h=a.Pa().labels();c=c?a.kb().labels().na:null;var k=a.Pa().labels().na,l=e&&$.n(e.enabled)?e.enabled:null,m=b&&$.n(b.enabled)?b.enabled:null,p=f&&null!==f.enabled()?f.enabled():null,q=h&&null!==h.enabled()?h.enabled():
null;a=!1;null!=l?a=l:null!=m?a=m:null!=p?a=p:a=q;a?(d.enabled(!0),d.state("labelOwnSettings",d.Oa,0),d.state("pointState",e,1),d.state("pointNormal",b,2),d.state("elementState",f,3),d.state("elementNormal",h,4),d.state("elementStateTheme",c,5),d.state("auto",d.Ld,6),d.state("elementNormalTheme",k,7)):d.enabled(!1);d.W()}};$.g.ps=function(){return[this]};$.g.bD=function(){return["from","to","weight"]};
$.g.F=function(){var a=gT.u.F.call(this);a.data=this.data().F();a.tooltip=this.Sa().F();a.palette=this.ac().F();a.dropoff=this.ZL().F();a.flow=this.oM().F();a.node=this.node().F();$.Xq(this,oT,a);return{chart:a}};$.g.U=function(a,b){gT.u.U.call(this,a,b);"data"in a&&this.data(a.data);this.ac(a.palette);"tooltip"in a&&this.Sa().fa(!!b,a.tooltip);"dropoff"in a&&this.ZL().fa(!!b,a.dropoff);"flow"in a&&this.oM().fa(!!b,a.flow);"node"in a&&this.node().fa(!!b,a.node);$.Pq(this,oT,a,b)};
$.g.R=function(){$.ud(this.Ea,this.j,this.b,this.f,this.fb,this.K,this.ba,this.P,this.ea,this.ka,this.Xc);this.fb=this.f=this.b=this.j=this.Ea=null;this.K.length=0;this.ba.length=0;this.P.length=0;this.ea.length=0;this.rd=this.Xc=this.ka=null;gT.u.R.call(this)};var pT=gT.prototype;pT.getType=pT.La;pT.data=pT.data;pT.noData=pT.Cm;pT.tooltip=pT.Sa;pT.dropoff=pT.ZL;pT.flow=pT.oM;pT.node=pT.node;pT.palette=pT.ac;$.Xp.sankey=Wfa;$.F("anychart.sankey",Wfa);}).call(this,$)}