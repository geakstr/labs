from flask import Flask, render_template
import rdflib 

app = Flask(__name__)
app.debug = True

g = rdflib.Graph()
g.load('./rdf.xml')

RDF = rdflib.namespace.RDF
APPS = rdflib.Namespace('http://local-appstore/rdf#')

@app.route("/")
def index_page():
    apps_list = []

    for triple in g.triples((rdflib.term.URIRef('http://local-appstore/all-apps'), None, None)):
        app_item = {}
        for app in filter(lambda y: RDF['type'] != y[1], g.triples((triple[2], None, None))):
            for title in g.triples((app[0], APPS['title'], app[2])):
                app_item['title'] = title[2]
            for price in g.triples((app[0], APPS['price'], app[2])):
                app_item['price'] = price[2]
            for url in g.triples((app[0], APPS['url'], app[2])):
                app_item['url'] = url[2]
            for description in g.triples((app[0], APPS['description'], app[2])):
                app_item['description'] = description[2]
            for size in g.triples((app[0], APPS['size'], app[2])):
                app_item['size'] = size[2]
        if len(app_item) > 0:
            apps_list.append(app_item)

    return render_template('index.html', apps=apps_list)

@app.route("/apps/<id>")
def app_page(id):
    app_item = {}
    for triple in g.triples((rdflib.term.URIRef('http://local-appstore/apps/' + id), None, None)):
        for title in g.triples((triple[0], APPS['title'], triple[2])):
            app_item['title'] = title[2]
        for price in g.triples((triple[0], APPS['price'], triple[2])):
            app_item['price'] = price[2]
        for url in g.triples((triple[0], APPS['url'], triple[2])):
            app_item['url'] = url[2]
        for description in g.triples((triple[0], APPS['description'], triple[2])):
            app_item['description'] = description[2]
        for size in g.triples((triple[0], APPS['size'], triple[2])):
            app_item['size'] = size[2]

    return render_template('app.html', app=app_item)

if __name__ == "__main__":
    app.run()
