<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Latte Maintenance</title>
        <meta name="viewport" content="width=device-width">
		<link rel="shortcut icon" href="/img/favicon.ico">
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

        <style>
            /*! normalize.css v1.0.2 | MIT License | git.io/normalize */
            
            /* ==========================================================================
               HTML5 display definitions
               ========================================================================== */
            
            /*
             * Corrects `block` display not defined in IE 6/7/8/9 and Firefox 3.
             */
            
            article,
            aside,
            details,
            figcaption,
            figure,
            footer,
            header,
            hgroup,
            nav,
            section,
            summary {
                display: block;
            }
            
            /*
             * Corrects `inline-block` display not defined in IE 6/7/8/9 and Firefox 3.
             */
            
            audio,
            canvas,
            video {
                display: inline-block;
                *display: inline;
                *zoom: 1;
            }
            
            /*
             * Prevents modern browsers from displaying `audio` without controls.
             * Remove excess height in iOS 5 devices.
             */
            
            audio:not([controls]) {
                display: none;
                height: 0;
            }
            
            /*
             * Addresses styling for `hidden` attribute not present in IE 7/8/9, Firefox 3,
             * and Safari 4.
             * Known issue: no IE 6 support.
             */
            
            [hidden] {
                display: none;
            }
            
            /* ==========================================================================
               Base
               ========================================================================== */
            
            /*
             * 1. Corrects text resizing oddly in IE 6/7 when body `font-size` is set using
             *    `em` units.
             * 2. Prevents iOS text size adjust after orientation change, without disabling
             *    user zoom.
             */
            
            html {
                font-size: 100%; /* 1 */
                -webkit-text-size-adjust: 100%; /* 2 */
                -ms-text-size-adjust: 100%; /* 2 */
            }
            
            /*
             * Addresses `font-family` inconsistency between `textarea` and other form
             * elements.
             */
            
            html,
            button,
            input,
            select,
            textarea {
                font-family: sans-serif;
            }
            
            /*
             * Addresses margins handled incorrectly in IE 6/7.
             */
            
            body {
                margin: 0;
            }
            
            /* ==========================================================================
               Links
               ========================================================================== */
            
            /*
             * Addresses `outline` inconsistency between Chrome and other browsers.
             */
            
            a:focus {
                outline: thin dotted;
            }
            
            /*
             * Improves readability when focused and also mouse hovered in all browsers.
             */
            
            a:active,
            a:hover {
                outline: 0;
            }
            
            /* ==========================================================================
               Typography
               ========================================================================== */
            
            /*
             * Addresses font sizes and margins set differently in IE 6/7.
             * Addresses font sizes within `section` and `article` in Firefox 4+, Safari 5,
             * and Chrome.
             */
            
            h1 {
                font-size: 2em;
                margin: 0.67em 0;
            }
            
            h2 {
                font-size: 1.5em;
                margin: 0.83em 0;
            }
            
            h3 {
                font-size: 1.17em;
                margin: 1em 0;
            }
            
            h4 {
                font-size: 1em;
                margin: 1.33em 0;
            }
            
            h5 {
                font-size: 0.83em;
                margin: 1.67em 0;
            }
            
            h6 {
                font-size: 0.67em;
                margin: 2.33em 0;
            }
            
            /*
             * Addresses styling not present in IE 7/8/9, Safari 5, and Chrome.
             */
            
            abbr[title] {
                border-bottom: 1px dotted;
            }
            
            /*
             * Addresses style set to `bolder` in Firefox 3+, Safari 4/5, and Chrome.
             */
            
            b,
            strong {
                font-weight: bold;
            }
            
            blockquote {
                margin: 1em 40px;
            }
            
            /*
             * Addresses styling not present in Safari 5 and Chrome.
             */
            
            dfn {
                font-style: italic;
            }
            
            /*
             * Addresses styling not present in IE 6/7/8/9.
             */
            
            mark {
                background: #ff0;
                color: #000;
            }
            
            /*
             * Addresses margins set differently in IE 6/7.
             */
            
            p,
            pre {
                margin: 1em 0;
            }
            
            /*
             * Corrects font family set oddly in IE 6, Safari 4/5, and Chrome.
             */
            
            code,
            kbd,
            pre,
            samp {
                font-family: monospace, serif;
                _font-family: 'courier new', monospace;
                font-size: 1em;
            }
            
            /*
             * Improves readability of pre-formatted text in all browsers.
             */
            
            pre {
                white-space: pre;
                white-space: pre-wrap;
                word-wrap: break-word;
            }
            
            /*
             * Addresses CSS quotes not supported in IE 6/7.
             */
            
            q {
                quotes: none;
            }
            
            /*
             * Addresses `quotes` property not supported in Safari 4.
             */
            
            q:before,
            q:after {
                content: '';
                content: none;
            }
            
            /*
             * Addresses inconsistent and variable font size in all browsers.
             */
            
            small {
                font-size: 80%;
            }
            
            /*
             * Prevents `sub` and `sup` affecting `line-height` in all browsers.
             */
            
            sub,
            sup {
                font-size: 75%;
                line-height: 0;
                position: relative;
                vertical-align: baseline;
            }
            
            sup {
                top: -0.5em;
            }
            
            sub {
                bottom: -0.25em;
            }
            
            /* ==========================================================================
               Lists
               ========================================================================== */
            
            /*
             * Addresses margins set differently in IE 6/7.
             */
            
            dl,
            menu,
            ol,
            ul {
                margin: 1em 0;
            }
            
            dd {
                margin: 0 0 0 40px;
            }
            
            /*
             * Addresses paddings set differently in IE 6/7.
             */
            
            menu,
            ol,
            ul {
                padding: 0 0 0 40px;
            }
            
            /*
             * Corrects list images handled incorrectly in IE 7.
             */
            
            nav ul,
            nav ol {
                list-style: none;
                list-style-image: none;
            }
            
            /* ==========================================================================
               Embedded content
               ========================================================================== */
            
            /*
             * 1. Removes border when inside `a` element in IE 6/7/8/9 and Firefox 3.
             * 2. Improves image quality when scaled in IE 7.
             */
            
            img {
                border: 0; /* 1 */
                -ms-interpolation-mode: bicubic; /* 2 */
            }
            
            /*
             * Corrects overflow displayed oddly in IE 9.
             */
            
            svg:not(:root) {
                overflow: hidden;
            }
            
            /* ==========================================================================
               Figures
               ========================================================================== */
            
            /*
             * Addresses margin not present in IE 6/7/8/9, Safari 5, and Opera 11.
             */
            
            figure {
                margin: 0;
            }
            
            /* ==========================================================================
               Forms
               ========================================================================== */
            
            /*
             * Corrects margin displayed oddly in IE 6/7.
             */
            
            form {
                margin: 0;
            }
            
            /*
             * Define consistent border, margin, and padding.
             */
            
            fieldset {
                border: 1px solid #c0c0c0;
                margin: 0 2px;
                padding: 0.35em 0.625em 0.75em;
            }
            
            /*
             * 1. Corrects color not being inherited in IE 6/7/8/9.
             * 2. Corrects text not wrapping in Firefox 3.
             * 3. Corrects alignment displayed oddly in IE 6/7.
             */
            
            legend {
                border: 0; /* 1 */
                padding: 0;
                white-space: normal; /* 2 */
                *margin-left: -7px; /* 3 */
            }
            
            /*
             * 1. Corrects font size not being inherited in all browsers.
             * 2. Addresses margins set differently in IE 6/7, Firefox 3+, Safari 5,
             *    and Chrome.
             * 3. Improves appearance and consistency in all browsers.
             */
            
            button,
            input,
            select,
            textarea {
                font-size: 100%; /* 1 */
                margin: 0; /* 2 */
                vertical-align: baseline; /* 3 */
                *vertical-align: middle; /* 3 */
            }
            
            /*
             * Addresses Firefox 3+ setting `line-height` on `input` using `!important` in
             * the UA stylesheet.
             */
            
            button,
            input {
                line-height: normal;
            }
            
            /*
             * 1. Avoid the WebKit bug in Android 4.0.* where (2) destroys native `audio`
             *    and `video` controls.
             * 2. Corrects inability to style clickable `input` types in iOS.
             * 3. Improves usability and consistency of cursor style between image-type
             *    `input` and others.
             * 4. Removes inner spacing in IE 7 without affecting normal text inputs.
             *    Known issue: inner spacing remains in IE 6.
             */
            
            button,
            html input[type="button"], /* 1 */
            input[type="reset"],
            input[type="submit"] {
                -webkit-appearance: button; /* 2 */
                cursor: pointer; /* 3 */
                *overflow: visible;  /* 4 */
            }
            
            /*
             * Re-set default cursor for disabled elements.
             */
            
            button[disabled],
            input[disabled] {
                cursor: default;
            }
            
            /*
             * 1. Addresses box sizing set to content-box in IE 8/9.
             * 2. Removes excess padding in IE 8/9.
             * 3. Removes excess padding in IE 7.
             *    Known issue: excess padding remains in IE 6.
             */
            
            input[type="checkbox"],
            input[type="radio"] {
                box-sizing: border-box; /* 1 */
                padding: 0; /* 2 */
                *height: 13px; /* 3 */
                *width: 13px; /* 3 */
            }
            
            /*
             * 1. Addresses `appearance` set to `searchfield` in Safari 5 and Chrome.
             * 2. Addresses `box-sizing` set to `border-box` in Safari 5 and Chrome
             *    (include `-moz` to future-proof).
             */
            
            input[type="search"] {
                -webkit-appearance: textfield; /* 1 */
                -moz-box-sizing: content-box;
                -webkit-box-sizing: content-box; /* 2 */
                box-sizing: content-box;
            }
            
            /*
             * Removes inner padding and search cancel button in Safari 5 and Chrome
             * on OS X.
             */
            
            input[type="search"]::-webkit-search-cancel-button,
            input[type="search"]::-webkit-search-decoration {
                -webkit-appearance: none;
            }
            
            /*
             * Removes inner padding and border in Firefox 3+.
             */
            
            button::-moz-focus-inner,
            input::-moz-focus-inner {
                border: 0;
                padding: 0;
            }
            
            /*
             * 1. Removes default vertical scrollbar in IE 6/7/8/9.
             * 2. Improves readability and alignment in all browsers.
             */
            
            textarea {
                overflow: auto; /* 1 */
                vertical-align: top; /* 2 */
            }
            
            /* ==========================================================================
               Tables
               ========================================================================== */
            
            /*
             * Remove most spacing between table cells.
             */
            
            table {
                border-collapse: collapse;
                border-spacing: 0;
            }
            
            /* ==========================================================================
               Custom Style
               ========================================================================== */
            
            /*
             * Replace the file name to change the full screen image
             */
             
             body {
                background:url(/lib/img/spilled-coffee.jpg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                
                -webkit-backface-visibility: hidden;
             }
             
             article {
                 position:fixed;
                 top:0;
                 left:0;
                 right:0;
                 bottom:0;
                 padding:20%;
                 background:rgba(0,0,0,.5);
             }
             
             article p {
                 -webkit-animation-duration: 1s;
                     -moz-animation-duration: 1s;
                       -o-animation-duration: 1s;
                          animation-duration: 1s;
                  -webkit-animation-fill-mode: both;
                     -moz-animation-fill-mode: both;
                       -o-animation-fill-mode: both;
                          animation-fill-mode: both;
                 -webkit-animation-name: fadeInUp;
                     -moz-animation-name: fadeInUp;
                         -o-animation-name: fadeInUp;
                             animation-name: fadeInUp;
                 font-family: "Helvetica Neue", Arial, Helvetica, sans-serif;
                 color:#fff;
                 text-shadow:0 1px 1px #000;
                 font-size:3em;
             }

             article p span {
                font-size:.8em;
             }
             
             @-webkit-keyframes fadeInUp {
                 0% {
                     opacity: 0;
                     -webkit-transform: translateY(20px);
                 }
             
                 100% {
                     opacity: 1;
                     -webkit-transform: translateY(0);
                 }
             }
             
             @-moz-keyframes fadeInUp {
                 0% {
                     opacity: 0;
                     -moz-transform: translateY(20px);
                 }
             
                 100% {
                     opacity: 1;
                     -moz-transform: translateY(0);
                 }
             }
             
             @-o-keyframes fadeInUp {
                 0% {
                     opacity: 0;
                     -o-transform: translateY(20px);
                 }
             
                 100% {
                     opacity: 1;
                     -o-transform: translateY(0);
                 }
             }
             
             @keyframes fadeInUp {
                 0% {
                     opacity: 0;
                     transform: translateY(20px);
                 }
             
                 100% {
                     opacity: 1;
                     transform: translateY(0);
                 }
             }
        </style>
    </head>
    <body onclick="history.back();">
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->
        <article>
            <p ><strong>We spilled some coffee.</strong><br/><span>Latte shall return shortly.</span></p>
        </article>
    </body>
</html> 
