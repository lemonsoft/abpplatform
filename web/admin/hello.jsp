<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>jQuery treetable with event trigger - jsFiddle demo by djlerman</title>

        <link rel="stylesheet" type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/screen.css">
        <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/jquery.treetable.css">
        <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/jquery.treetable.theme.default.css">

        <script type="text/javascript"   src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
        <script type='text/javascript' src="http://ludo.cubicphuse.nl/jquery-treetable/jquery.treetable.js"></script>

        <script type='text/javascript'>//<![CDATA[ 
            $(window).load(function () {
                $("#example-basic").treetable({expandable: true});

                /* 
                 Trying to get this function to happen when 
                 "onNodeCollapse" event happens
                 */
                $("#example-basic").treetable({
                    expandable: true,
                    onNodeCollapse: function () {
                        alert("A branch has collapsed");
                    }
                });
            });//]]>  
        </script>
    </head>
    <body>
        <table id="example-basic">
            <caption>Basic jQuery treetable Example</caption>
            <thead>
                <tr>
                    <th>Tree column</th>
                    <th>Additional data</th>
                </tr>
            </thead>
            <tbody>
                <tr data-tt-id="1">
                    <td>Node 1: Click on the icon in front of me to expand this branch.</td>
                    <td>I live in the second column.</td>
                </tr>
                <tr data-tt-id="1.1" data-tt-parent-id="1">
                    <td>Node 1.1: Look, I am a table row <em>and</em> I am part of a tree!</td>
                    <td>Interesting.</td>
                </tr>
                <tr data-tt-id="1.1.1" data-tt-parent-id="1.1">
                    <td>Node 1.1.1: I am part of the tree too!</td>
                    <td>That's it!</td>
                </tr>
                <tr data-tt-id="2">
                    <td>Node 2: I am another root node, but without children</td>
                    <td>Hurray!</td>
                </tr>
            </tbody>
        </table>

    </body>

</html>