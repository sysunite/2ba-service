<html>
<head>
    <title>Examine 2ba product</title>
    <link rel="stylesheet" href="http://yegor256.github.io/tacit/tacit.min.css"/>
</head>
<body>


<h3>Product ${product.getId()}</h3>
<h4>Details</h4>
<table>

    <thead>
    <tr class="row-header">
        <th>Brand</th>
        <th>Description</th>
        <th>GTIN</th>
        <th>Manufacturer Name</th>
        <th>Manufacturer GLN</th>
        <th>Model</th>
        <th>Modeling Class</th>
    </tr>
    </thead>

    <tbody>

        <tr>
            <td>${product.getBrand()!"null"}</td>
            <td>${product.getDescription()!"null"}</td>
            <td>${product.getGTIN()!"null"}</td>
            <td>${product.getManufacturerName()!"null"}</td>
            <td>${product.getManufacturerGLN()!"null"}</td>
            <td>${product.getModel()!"null"}</td>
            <td>${product.getModellingClass()!"null"}</td>
        </tr>

    </tbody>
</table>




<h4 style="margin-top: 70px">Null Features</h4>
<table>

    <thead>
    <tr class="row-header">
        <th>EF Code</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    </thead>

    <tbody>

    <#--<#list features2ba as feature>-->
    <#--<tr>-->
        <#--<td>${feature.getDescription()}</td>-->
        <#--<td>${feature.getDimensionalDrawingCode()}</td>-->
        <#--<td>${feature.getUnitShortNotation()}</td>-->
        <#--<td>${feature.getNumericValue()}</td>-->
    <#--</tr>-->
    <#--</#list>-->
    </tbody>
</table>



<h4 style="margin-top: 70px">Numerical Features</h4>
<table>

    <thead>
    <tr class="row-header">
        <th>EF Code</th>
        <th>Drawing Code</th>
        <th>Description</th>
        <th>Value</th>
        <th>Unit</th>
    </tr>
    </thead>

    <tbody>

    <#list numericalFeatures as feature>
    <tr>
        <td>${feature.getFeatureID()!"null"}</td>
        <td>${feature.getDimensionalDrawingCode()!""}</td>
        <td>${feature.getDescription()!""}</td>
        <td>${feature.getNumericValue()!"null"}</td>
        <td>${feature.getUnitShortNotation()!""}</td>
    </tr>
    </#list>
    </tbody>
</table>




</body>
</html>
