package de.uniulm.bagception.location.osm;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

public class TouchMapView extends MapView {
	
	private ItemizedOverlay<OverlayItem> overlays;
	private ArrayList<OverlayItem> itemList;
	
	private int lastEventId = -1;	
	
	public TouchMapView(Context context, int tileSizePixels) {
		super(context, tileSizePixels);

	}
	
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == 1 && lastEventId == 0 && event.getPointerCount()==1){
			Log.d("touch","tap");
			tap((int)event.getX(),(int)event.getY());
		}
		lastEventId = event.getAction();
		return super.onTouchEvent(event);
	}
	
	private void tap(int x, int y){
		IGeoPoint p = TouchMapView.this.getProjection().fromPixels((int) x,
				(int) y);

		GeoPoint pnt = new GeoPoint(p.getLatitudeE6(),p.getLongitudeE6());
		placeMarker(pnt);
		
		
	}
	
	public void placeMarker(GeoPoint pnt){
		OverlayItem markerHere = new OverlayItem("location", "", pnt);
		
		getOverlays().clear();
		
		itemList = new ArrayList<OverlayItem>();
		itemList.add(markerHere);
		overlays = new TouchItemizedOverlay(getContext(),itemList);
		getOverlays().add(overlays);
		
		//itemList.add(touchHere);
		this.invalidate();
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