package de.uniulm.bagception.location.osm;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

        
        super.onCreate(savedInstanceState);
        
        MapView mapView = new MapView(this, 256);
        mapView.setClickable(true);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
 
        mapView.getController().setZoom(18);
        mapView.getController().setCenter(new GeoPoint(48.402067,9.99395));
 
     
        
        setContentView(mapView);
		
	}
	
	
	



	

}


