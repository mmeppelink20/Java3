$(".btn-open").on("click", function() {
    let title = $(this).attr("data-title");
    let population = $(this).attr("data-population");
    let region = $(this).attr("data-region");
    let latitude = $(this).attr("data-latitude");
    let longitude = $(this).attr("data-longitude");
    let area = $(this).attr("data-area");
    let zoom = 10;
    if(area <= 100000) {
        zoom = 6;
    } else if(area <= 2500000 && area > 100000) {
        zoom = 5;
    } else if(area <= 7692024 && area > 2500000) {
        zoom = 4;
    }
    else if(area <= 17098242 && area > 7692024) {
        zoom = 3;
    }


    // console.log(`${title} ${population} ${region}`);
    $(".modal-title").html(title);
    $(".modal-population").html(population);
    $(".modal-region").html(region);
    $(".modal-map").html(`
            <iframe 
            width="100%" 
            height="480" 
            frameborder="0" 
            scrolling="no" 
            marginheight="0" 
            marginwidth="0"
            src="https://maps.google.com/maps?q=${latitude},${longitude}&amp;z=${zoom}&amp;output=embed">
            <br>
            <a href="https://www.google.com/maps/@${latitude},${longitude},1z" target="_blank">
            See full page map
            </a></iframe>
    `);
});