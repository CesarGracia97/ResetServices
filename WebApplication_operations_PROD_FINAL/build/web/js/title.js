// JavaScript Document
function retornarFecha()
{
  var fecha
  fecha=new Date();
  var cadena=fecha.getDate()+'/'+(fecha.getMonth()+1)+'/'+fecha.getYear();
  return cadena;
}

function retornarHora() { 
	var fecha=new Date(); 
	var cadena=fecha.getHours()+':'+fecha.getMinutes()+':'+fecha.getSeconds(); 
	return cadena; 
} 


// set the namespace
var XHTMLNS = 'http://www.w3.org/1999/xhtml';
var CURRENT_NICE_TITLE;

// browser sniff
var browser = new Browser();



// determine browser and version.
function Browser()
	{
	var ua, s, i;

	this.isIE = false;
	this.isNS = false;
	this.version = null;

	ua = navigator.userAgent;

	s = 'MSIE';
	if ((i = ua.indexOf(s)) >= 0)
		{
		this.isIE = true;
		this.version = parseFloat(ua.substr(i + s.length));
		return;
		}

	s = 'Netscape6/';
	if ((i = ua.indexOf(s)) >= 0)
		{
		this.isNS = true;
		this.version = parseFloat(ua.substr(i + s.length));
		return;
		}

	// treat any other 'Gecko' browser as NS 6.1.
	s = 'Gecko';
	if ((i = ua.indexOf(s)) >= 0)
		{
		this.isNS = true;
		this.version = 6.1;
		return;
		}
	}



// 2003-11-19 sidesh0w
// set delay vars to emulate normal hover delay
var delay;
var interval = 0.60;



// this function runs on window load
// it runs through all the links on the page as starts listening for actions
function makeNiceTitles()
	{
	if (!document.createElement || !document.getElementsByTagName) return;
	if (!document.createElementNS)
		{
		document.createElementNS = function(ns, elt)
			{
			return document.createElement(elt);
			}
		}

	// do regular links
	if (!document.links)
		{
		document.links = document.getElementsByTagName('a');
		}
	for (var ti=0; ti<document.links.length; ti++)
		{
		var lnk = document.links[ti];
		if (lnk.title)
			{
			lnk.setAttribute('nicetitle', lnk.title);
			lnk.removeAttribute('title');
			addEvent(lnk, 'mouseover', showDelay);
			addEvent(lnk, 'mouseout', hideNiceTitle);
			addEvent(lnk, 'focus', showDelay);
			addEvent(lnk, 'blur', hideNiceTitle);
			}
		}

	// 2003-03-25 Peter Janes
	// do ins and del tags
	var tags = new Array(2);
	tags[0] = document.getElementsByTagName('ins');
	tags[1] = document.getElementsByTagName('del');
	for (var tt=0; tt<tags.length; tt++)
		{
		if (tags[tt])
			{
			for (var ti=0; ti<tags[tt].length; ti++)
				{
				var tag = tags[tt][ti];
				if (tag.dateTime)
					{
					var strDate = tag.dateTime;
					// HTML/ISO8601 date: yyyy-mm-ddThh:mm:ssTZD (Z, -hh:mm, +hh:mm)
					var month = strDate.substring(5,7);
					var day = strDate.substring(8,10);
					if (month[0] == '0')
						{
						month = month[1];
						}
					if (day[0] == '0')
						{
						day = day[1];
						}
					var dtIns = new Date(strDate.substring(0,4), month-1, day, strDate.substring(11,13), strDate.substring(14,16), strDate.substring(17,19));
					tag.setAttribute('nicetitle', (tt == 0 ? 'Added' : 'Deleted') + ' on ' + dtIns.toString());
					addEvent(tag, 'mouseover', showDelay);
					addEvent(tag, 'mouseout', hideNiceTitle);
					addEvent(tag, 'focus', showDelay);
					addEvent(tag, 'blur', hideNiceTitle);
					}
				}
			}
		}
	}



// by Scott Andrew
// add an eventlistener to browsers that can do it somehow.
function addEvent(obj, evType, fn)
	{
	if (obj.addEventListener)
		{
		obj.addEventListener(evType, fn, true);
		return true;
		}
	else if (obj.attachEvent)
		{
		var r = obj.attachEvent('on'+evType, fn);
		return r;
		}
	else
		{
		return false;
		}
	}



function findPosition(oLink)
	{
	if (oLink.offsetParent)
		{
		for (var posX = 0, posY = 0; oLink.offsetParent; oLink = oLink.offsetParent)
			{
			posX += oLink.offsetLeft;
			posY += oLink.offsetTop;
			}
		return [posX, posY];
		}
	else
		{
		return [oLink.x, oLink.y];
		}
	}



function getParent(el, pTagName)
	{
	if (el == null)
		{
		return null;
		}
	// gecko bug, supposed to be uppercase
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())
		{
		return el;
		}
	else
		{
		return getParent(el.parentNode, pTagName);
		}
	}



// 2003-11-19 sidesh0w
// trailerpark wrapper function
function showDelay(e)
	{
    if (window.event && window.event.srcElement)
		{
        lnk = window.event.srcElement
		}
	else if (e && e.target)
		{
        lnk = e.target
		}
    if (!lnk) return;

	// lnk is a textnode or an elementnode that's not ins/del
    if (lnk.nodeType == 3 || (lnk.nodeType == 1 && lnk.tagName.toLowerCase() != 'ins' && lnk.tagName.toLowerCase() != 'del'))
		{
		// ascend parents until we hit a link
		lnk = getParent(lnk, 'a');
		}
	
	delay = setTimeout("showNiceTitle(lnk)", interval * 1000);
	}



// build and show the nice titles
function showNiceTitle(link)
	{
    if (CURRENT_NICE_TITLE) hideNiceTitle(CURRENT_NICE_TITLE);
    if (!document.getElementsByTagName) return;

    nicetitle = lnk.getAttribute('nicetitle');
    
    var d = document.createElementNS(XHTMLNS, 'div');
    d.className = 'nicetitle';
    tnt = document.createTextNode(nicetitle);
    pat = document.createElementNS(XHTMLNS, 'p');
    pat.className = 'titletext';
    pat.appendChild(tnt);

	// 2003-11-18 Dunstan Orchard
	// added in accesskey info
	if (lnk.accessKey)
		{
        axs = document.createTextNode(' [' + lnk.accessKey + ']');
		axsk = document.createElementNS(XHTMLNS, 'span');
        axsk.className = 'accesskey';
        axsk.appendChild(axs);
		pat.appendChild(axsk);
		}
    d.appendChild(pat);

    if (lnk.href)
		{
        tnd = document.createTextNode(lnk.href);
        pad = document.createElementNS(XHTMLNS, 'p');
        pad.className = 'destination';
        pad.appendChild(tnd);
        d.appendChild(pad);
		}
    
    STD_WIDTH = 300;

	if (lnk.href)
		{
        h = lnk.href.length;
		}
	else
		{
		h = nicetitle.length;
		}
	
    if (nicetitle.length)
		{
		t = nicetitle.length;
		}
	
    h_pixels = h*6;
	t_pixels = t*10;
    
    if (h_pixels > STD_WIDTH)
		{
        w = h_pixels;
		}
	else if ((STD_WIDTH>t_pixels) && (t_pixels>h_pixels))
		{
        w = t_pixels;
		}
	else if ((STD_WIDTH>t_pixels) && (h_pixels>t_pixels))
		{
        w = h_pixels;
		}
	else
		{
        w = STD_WIDTH;
		}
        
    d.style.width = w + 'px';    

    mpos = findPosition(lnk);
    mx = mpos[0];
    my = mpos[1];
    
    d.style.left = (mx+15) + 'px';
    d.style.top = (my+35) + 'px';

    if (window.innerWidth && ((mx+w) > window.innerWidth))
		{
        d.style.left = (window.innerWidth - w - 25) + 'px';
		}
    if (document.body.scrollWidth && ((mx+w) > document.body.scrollWidth))
		{
        d.style.left = (document.body.scrollWidth - w - 25) + 'px';
		}
    
    document.getElementsByTagName('body')[0].appendChild(d);

    CURRENT_NICE_TITLE = d;
	}




function hideNiceTitle(e)
	{
	// 2003-11-19 sidesh0w
	// clearTimeout 
	if (delay) clearTimeout(delay);
	if (!document.getElementsByTagName) return;
	if (CURRENT_NICE_TITLE)
		{
		document.getElementsByTagName('body')[0].removeChild(CURRENT_NICE_TITLE);
		CURRENT_NICE_TITLE = null;
		}
	}

window.onload = function(e) {
makeNiceTitles();
}


function verificarLinea(){
	
		if(form1.txtLinea.value.replace(/ /g, '') == ''){
			alert("Favor: Debe Ingresar una Linea ");
			form1.txtLinea.focus();
			form1.txtLinea.select();
			return false;
		}
		
		if( !/^([0-9])*$/.test( form1.txtLinea.value ) ){
			alert("Solo se aceptan valores numericos...");
			form1.txtLinea.focus();
			form1.txtLinea.select();
			return false;
		}
		
	} 
	
	
function Disab (val) {
	if(val=="1"){
		form1.txtPin.disabled=false;
		form1.txtPin.focus();
		form1.txtTelefono.disabled=true;
		//form1.txtTelefono.value='';
	}
	if(val=="2"){
		form1.txtPin.disabled=true;
		form1.txtTelefono.disabled=false;
		form1.txtTelefono.focus();
		//form1.txtPin.value='';
	}
}

