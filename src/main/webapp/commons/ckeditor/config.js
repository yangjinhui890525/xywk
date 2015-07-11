/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.language = 'zh-cn' ;
/*	config.LinkBrowserURL = "/commons/ckeditor/" + "filemanager/browser/default/browser.html?Connector=/commons/ckeditor/connector" ;
	config.ImageBrowserURL = "/commons/ckeditor/" + "filemanager/browser/default/browser.html?Type=Image&Connector=/commons/ckeditor/connector" ;
	config.FlashBrowserURL = "/commons/ckeditor/" + "filemanager/browser/default/browser.html?Type=Flash&Connector=/commons/ckeditor/connector" ;
	config.LinkUploadURL = '/commons/ckeditor/simpleuploader?Type=File' ;
	config.FlashUploadURL = '/commons/ckeditor/simpleuploader?Type=Flash' ;
	config.ImageUploadURL = '/commons/ckeditor/simpleuploader?Type=Image' ;*/
	 config.filebrowserBrowseUrl = fq.contextPath+'/commons/ckeditor/uploader/browse.jsp ';   
    config.filebrowserImageBrowseUrl = fq.contextPath+'/commons/ckeditor/uploader/browse.jsp?type=Images';   
    config.filebrowserFlashBrowseUrl = fq.contextPath+'/commons/ckeditor/uploader/browse.jsp?type=Flashs';   
    config.filebrowserUploadUrl = fq.contextPath+'/commons/ckeditor/uploader/upload.jsp';   
    config.filebrowserImageUploadUrl = fq.contextPath+'/commons/ckeditor/uploader/upload.jsp?type=Images';   
    config.filebrowserFlashUploadUrl = fq.contextPath+'/commons/ckeditor/uploader/upload.jsp?type=Flashs';  
    config.image_previewText=' '
};
