/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn' ;
	 config.filebrowserBrowseUrl = '/xywk/commons/ckeditor/uploader/browse.jsp ';   
    config.filebrowserImageBrowseUrl = 'xywk/commons/ckeditor/uploader/browse.jsp?type=Images';   
    config.filebrowserFlashBrowseUrl = '/xywk/commons/ckeditor/uploader/browse.jsp?type=Flashs';   
    config.filebrowserUploadUrl = '/xywk/commons/ckeditor/uploader/upload.jsp';   
    config.filebrowserImageUploadUrl ='/xywk/commons/ckeditor/uploader/upload.jsp?type=Images';   
    config.filebrowserFlashUploadUrl = '/xywk/commons/ckeditor/uploader/upload.jsp?type=Flashs';  
    config.image_previewText=' '
};
