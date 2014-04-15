function formatSuggestedCollection(collection) {
  var isNew = collection.id && collection.id.indexOf("-999999") == 0;
  var markup = "<table><tbody>";
  markup += "<tr><td>";
  if (!isNew) {
    if (collection.icon) {
      markup += "<img src='" + window.nxContextPath
          + collection.icon + "'/>"
    }
  } else {
    markup += "<img src='" + window.nxContextPath + "/icons/action_add.gif'/>"
  }
  markup += "</td><td>";
  markup += collection.displayLabel;
  markup += "</td></tr></tbody></table>";
  return markup;
}

var formatSelectedCollection = formatSuggestedCollection;