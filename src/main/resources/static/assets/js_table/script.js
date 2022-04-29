// Note :
// !$("#wrapper").hasClass("toggled") -> Sidebar Open
// $("#wrapper").hasClass("toggled") -> Sidebar Closed
// $("overlay").is(":visible") -> Overlay Visible
// $("overlay").is(":hidden") -> Overlay Hidden

$(document).ready(function(){
	var containerWidth = $(window).width();
	if (containerWidth <= 768) {
		$("#overlay").show();
	}

	var dh = document.getElementsByClassName("list-menu-dropdown-header");
	var i;

	for (i = 0; i < dh.length; i++){
		dh[i].addEventListener("click", function(){
			var dd = this.nextElementSibling;
			dd.classList.toggle("dropdown-open");

			var arrow = this.lastElementChild.firstElementChild;
			arrow.classList.toggle("rotate180");
		});
	}

	var scrollbarWidth = $(document).width() - $(window).innerWidth();
	var sidebarPadding = (50 - scrollbarWidth) / 2;
	$(".sidebar-heading").css("padding-left", sidebarPadding);
	$(".sidebar-heading").css("padding-right", sidebarPadding + scrollbarWidth);
	$(".sidebar-heading").css("padding-top", sidebarPadding);
	$(".sidebar-heading").css("padding-bottom", sidebarPadding);

	// Has vertical scrollbar
	if ($(document).height() > $(window).height()) {
		$(".navbar").css("padding-right", 15);
		$(".container-fluid").css("padding-top", 15);
		$(".container-fluid").css("padding-left", 15);
		$(".container-fluid").css("padding-right", 15);
		$(".container-fluid").css("padding-bottom", 15);
	}
	
	customTextarea();
	$("#body-response").html("Hit Send to get a response!");
	$("#body-feedback").html("Hit Generate to get a response!");
});

$(window).resize(function(){
	var containerWidth = $(window).width();
	// Window > 768 px
	if (containerWidth > 768) {
		// Open sidebar if sidebar is closed & always hide overlay
		if ($("#wrapper").hasClass("toggled")) $("#wrapper").toggleClass("toggled");
		$("#overlay").hide();
	}
	// Windows <= 768 px
	else if (containerWidth <= 768) {
		// Close sidebar when sidebar is open & overlay is hidden
		if (!$("#wrapper").hasClass("toggled") && $("#overlay").is(":hidden")) $("#wrapper").toggleClass("toggled");
	}
});

$("#menu-toggle").click(function(e) {
	e.preventDefault();
	$("#wrapper").toggleClass("toggled");
	var containerWidth = $(window).width();
	// Show overlay when windows <= 1200 px and sidebar open
	if (containerWidth <= 768 && !$("#wrapper").hasClass("toggled")) {
		$("#overlay").show();
	}
});

$("#overlay").click(function(){
	// Close sidebar & hide overlay
	if (!$("#wrapper").hasClass("toggled")) $("#wrapper").toggleClass("toggled")
	$("#overlay").hide();
});

$(".custom-file-input").on("change", function() {
	var fileNames = $(this);
	var files = fileNames[0].files;

	if (files.length <= 0) {
		$(this).siblings(".custom-file-label").addClass("selected").html("No files chosen");
	}
	else {
		var label = files[0].name;
		for (let i = 1; i < files.length; i++) {
			label = label + ", " + files[i].name;
		}
		label = "(" + label + ")";
		$(this).siblings(".custom-file-label").addClass("selected").html(String(files.length) + " File(s) " + label);	
	}
});

function customTextarea() {
	var enabled = true;
	$("textarea.tabSupport").keydown(function(e) {

	// Escape key toggles tab on/off
	if (e.keyCode==27) {
		enabled = !enabled;
		return false;
	}

	// Enter Key?
	if (e.keyCode === 13 && enabled) {
		// selection?
		if (this.selectionStart == this.selectionEnd) {
			// find start of the current line
			var sel = this.selectionStart;
			var selStart = this.selectionStart;
			var selEnd = this.selectionEnd;
			var text = $(this).val();
			while (sel > 0 && text[sel-1] != '\n')
				sel--;

			var lineStart = sel;
			while (text[sel] == ' ' || text[sel]=='\t')
				sel++;

			if (text[selStart-1] == '{' && text[selEnd] == '}') {
				document.execCommand('insertText', false, "\n\t\n" + text.substr(lineStart, sel-lineStart));
				this.selectionStart = selStart + 2;
				this.selectionEnd = this.selectionStart;
				return false;
			}

			if (text[selStart-1] == '[' && text[selEnd] == ']') {
				document.execCommand('insertText', false, "\n\t\n" + text.substr(lineStart, sel-lineStart));
				this.selectionStart = selStart + 2;
				this.selectionEnd = this.selectionStart;
				return false;
			}

			if (sel > lineStart) {
				// Insert carriage return and indented text
				document.execCommand('insertText', false, "\n" + text.substr(lineStart, sel-lineStart));

				// Scroll caret visible
				this.blur();
				this.focus();
				return false;
			}
		}
	}

	// Tab key?
	if(e.keyCode === 9 && enabled) {
		// selection?
		if (this.selectionStart == this.selectionEnd) {
			// These single character operations are undoable
			if (!e.shiftKey) {
				document.execCommand('insertText', false, "\t");
			}
			else {
				var text = this.value;
				if (this.selectionStart > 0 && text[this.selectionStart-1]=='\t') {
					document.execCommand('delete');
				}
			}
		}
		else {
			// Block indent/unindent trashes undo stack.
			// Select whole lines
			var selStart = this.selectionStart;
			var selEnd = this.selectionEnd;
			var text = $(this).val();
			while (selStart > 0 && text[selStart-1] != '\n')
				selStart--;
			while (selEnd > 0 && text[selEnd-1]!='\n' && selEnd < text.length)
				selEnd++;

			// Get selected text
			var lines = text.substr(selStart, selEnd - selStart).split('\n');

			// Insert tabs
			for (var i=0; i<lines.length; i++) {
				// Don't indent last line if cursor at start of line
				if (i==lines.length-1 && lines[i].length==0)
					continue;

				// Tab or Shift+Tab?
				if (e.shiftKey) {
					if (lines[i].startsWith('\t'))
						lines[i] = lines[i].substr(1);
					else if (lines[i].startsWith("    "))
						lines[i] = lines[i].substr(4);
				}
				else
					lines[i] = "\t" + lines[i];
			}
			lines = lines.join('\n');

			// Update the text area
			this.value = text.substr(0, selStart) + lines + text.substr(selEnd);
			this.selectionStart = selStart;
			this.selectionEnd = selStart + lines.length; 
		}

		return false;
	}

	// Home key
	if (e.key === 'Home' && enabled) {
		if (this.selectionStart != this.selectionEnd && !e.shiftKey) {
			this.selectionEnd = this.selectionStart;
			return false;
		}

		var sel = this.selectionStart;
		var selStart = this.selectionStart;
		var text = $(this).val();

		do {
			if (text[sel-2] == '\n'){
				sel = sel-1;
				break;
			}
			sel--;
		} while(sel > 0 && text[sel-1] != '\t' && text[sel-1] != '\n');

		if (e.shiftKey){
			this.selectionStart = sel;
		}			
		else 
			this.selectionStart = this.selectionEnd = sel;
		return false; 
	}

	// { key
	if (e.key === '{' && enabled) {
		var selStart = this.selectionStart;
		var selEnd = this.selectionEnd;
		var text = $(this).val();

		var flag = false;
		// Check if there's character beside
		if (text.length > 0 && text.length > selStart-1 && [' ', '\t', '\n', '{', '[', '"'].indexOf(text[selStart-1]) == -1){
			flag = true;
		}
		if (text.length > 0 && text.length > selEnd && [' ', '\t', '\n', '}', ']', '"'].indexOf(text[selEnd]) == -1) {
			flag = true;
		}
		if (flag) {
			document.execCommand('insertText', false, '{');
			return false;
		}

		document.execCommand('insertText', false, "{}");
		this.selectionStart = this.selectionEnd - 1;
		this.selectionEnd = this.selectionEnd -1;
		return false;
	}

	// [ key
	if (e.key === '[' && enabled) {
		var selStart = this.selectionStart;
		var selEnd = this.selectionEnd;
		var text = $(this).val();
		
		var flag = false;
		// Check if there's character beside
		if (text.length > 0 && text.length > selStart-1 && [' ', '\t', '\n', '{', '[', '"'].indexOf(text[selStart-1]) == -1){
			flag = true;
		}
		if (text.length > 0 && text.length > selEnd && [' ', '\t', '\n', '}', ']', '"'].indexOf(text[selEnd]) == -1) {
			flag = true;
		}
		if (flag) {
			document.execCommand('insertText', false, '[');
			return false;
		}

		document.execCommand('insertText', false, "[]");
		this.selectionStart = this.selectionEnd - 1;
		this.selectionEnd = this.selectionEnd -1;
		return false;
	}

	// "" key
	if (e.key === '"' && enabled) {
		var selStart = this.selectionStart;
		var selEnd = this.selectionEnd;
		var text = $(this).val();

		var flag = false;
		// Check if there's character beside
		if (text.length > 0 && text.length > selStart-1 && [' ', '\t', '\n', '{', '['].indexOf(text[selStart-1]) == -1){
			flag = true;
		}
		if (text.length > 0 && text.length > selEnd && [' ', '\t', '\n', '}', ']'].indexOf(text[selEnd]) == -1) {
			flag = true;
		}
		if (flag) {
			document.execCommand('insertText', false, '"');
			return false;
		}

		document.execCommand('insertText', false, '""');
		this.selectionStart = this.selectionEnd - 1;
		this.selectionEnd = this.selectionEnd - 1;
		return false;
	}

	// Backspace key
	if (e.key === 'Backspace' && enabled) {
		var selStart = this.selectionStart;
		var selEnd = this.selectionEnd;
		var text = $(this).val();

		if (text[selStart-1] == '{' && text[selEnd] == '}') {
			this.selectionStart = this.selectionStart - 1;
			this.selectionEnd = this.selectionEnd + 1;
			document.execCommand('delete', false, null);
			return false;
		}

		if (text[selStart-1] == '[' && text[selEnd] == ']') {
			this.selectionStart = this.selectionStart - 1;
			this.selectionEnd = this.selectionEnd + 1;
			document.execCommand('delete', false, null);
			return false;
		}

		if (text[selStart-1] == '"' && text[selEnd] == '"') {
			this.selectionStart = this.selectionStart - 1;
			this.selectionEnd = this.selectionEnd + 1;
			document.execCommand('delete', false, null);
			return false;
		}
	}

	enabled = true;
	return true;
	});
}