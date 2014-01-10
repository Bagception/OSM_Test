package de.uniulm.bagception.location.osm;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MainActivity extends Activity implements LocationListener {

	LocationManager locManager;
	LocationListener locListener;
	String latitude;
	String longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		final TouchMapView mapView = new TouchMapView(this, 256);
		mapView.setClickable(true);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setBuiltInZoomControls(true);
	
		locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		mapView.getController().setZoom(18);

		locListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				location.getLatitude();
				location.getLongitude();

				latitude = String.valueOf(location.getLatitude());
				longitude = String.valueOf(location.getLongitude());

				double newLat = Double.parseDouble(latitude);
				double newLon = Double.parseDouble(longitude);
				GeoPoint here = new GeoPoint(newLat, newLon);
				mapView.getController().setCenter(here);
				mapView.placeMarker(here);
				locManager.removeUpdates(this);
				
				
			}
		};

		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				4000, 0, locListener);
		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				4000, 0, locListener);

		setContentView(mapView);

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}