package de.uniulm.bagception.location.osm;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TouchMapView extends MapView {
	
	private ItemizedOverlay<OverlayItem> overlays;
	private  ArrayList<OverlayItem> itemList;
	
	public TouchMapView(Context context, int tileSizePixels) {
		super(context, tileSizePixels);
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//if (event.getAction() == 1){
					IGeoPoint p = TouchMapView.this.getProjection().fromPixels((int) event.getX(),
							(int) event.getY());

					Toast.makeText(getContext(), p.getLatitude() + " " + p.getLongitude(),
							Toast.LENGTH_SHORT).show();
					GeoPoint pnt = new GeoPoint(p.getLatitudeE6(),p.getLongitudeE6());
					
					OverlayItem touchHere = new OverlayItem("location center", "", pnt);
					TouchMapView.this.getOverlays().clear();
					itemList = new ArrayList<OverlayItem>();
					TouchMapView.this.getOverlays().add(new TouchItemizedOverlay(getContext(),itemList));
					itemList.add(touchHere);
					TouchMapView.this.invalidate();
	
				//}
				return false;
			}
		});
		
		
	}
	
	

		
	
	private class TouchItemizedOverlay extends ItemizedIconOverlay<OverlayItem>{

		public TouchItemizedOverlay(Context pContext,List<OverlayItem> pList) {
			super(pContext,pList,new OnItemGestureListener<OverlayItem>() {

				@Override
				public boolean onItemLongPress(int arg0, OverlayItem arg1) {
					Log.d("touch","whyyyyyyyyyyyyyy");

					return false;
				}

				@Override
				public boolean onItemSingleTapUp(int arg0, OverlayItem arg1) {
					Log.d("touch","ääääääääääääää");

					return false;
				}
			});
			
		}

		

	}

}