document.addEventListener('DOMContentLoaded', (event) => {
    // Cargar el notebook por defecto al iniciar la página
    loadNotebook('notebookInicio.html');
});

/** * 
Carga un notebook en el iframe 
* @param  notebookFile - Nombre del archivo a cargar  
*/ 
function loadNotebook(notebookFile) {
    const iframe = document.getElementById('notebookViewer');
    const url = 'html_outputs/' + notebookFile; // Ruta del archivo a cargar
    console.log('Cargando URL:', url);
    iframe.src = url;
    console.log('Comprobar url:', iframe.src);
}

if (typeof global !== 'undefined') {
    module.exports = { loadNotebook };
}
