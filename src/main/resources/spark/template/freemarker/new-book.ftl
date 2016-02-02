<#import "masterTemplate.ftl" as layout/>

<@layout.masterTemplate>
<div>
    <h2>${message}</h2>
    <form action="/books/new" method="post">
        <label for="author">Author</label><br/>
        <input id="author" name="author" type="text"><br/>

        <label for="title">Book Title</label><br/>
        <input id="title" name="title" type="text"><br/>

        <input type="submit" text="Add"/>
    </form>
</div>
</@layout.masterTemplate>