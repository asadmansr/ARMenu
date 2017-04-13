using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class GUIElement : MonoBehaviour{

	private GUIStyle buttonStyle = null;
	private GameObject myImageTarget;
	private string mFoodName;	
	GameObject myBurger, myLambChops, myBacon,  myBSplit, mySushi, myPlate, myPlane;

	void Start () {
		myImageTarget = GameObject.Find ("ARplane");

		myBurger = GameObject.Find ("Burger");
		myLambChops = GameObject.Find ("Lamb Chops");
		myBacon = GameObject.Find ("Bacon and Eggs");
		mySushi = GameObject.Find ("Sushi");
		myBSplit = GameObject.Find ("Banana Split");

	}

	void Update () {

		update_mFoodName ();		//update the mFoodName variable for currently visible food item

	}

	void OnGUI(){

		configureStyle();

		if (myImageTarget.gameObject.GetComponent<Renderer> ().enabled == true) {

			if (GUI.Button (new Rect (Screen.width / 2 - 150, Screen.height - 150, 300, 100), "Order", buttonStyle)) {

				//Take Screenshot
				Application.CaptureScreenshot(Application.persistentDataPath + "/Order_Screenshot.png");


				AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
				AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
				AndroidJavaClass main = new AndroidJavaClass ("com.PulsarLabs.ARMenu.ReceiverActivity"); //Will change this to our package
				main.CallStatic ("ReceiveIntent", mFoodName, jo); //Will pass the type of food as string

			}
		}
	}

	private void configureStyle()
	{
		if ( buttonStyle == null)
		{
			buttonStyle = new GUIStyle("button");
			buttonStyle.fontSize = 50;
			GUILayout.Button ("button", GUILayout.Width (400), GUILayout.Height (250));
		}
	}

	private Texture2D MakeTex( int width, int height, Color col )
	{
		Color[] pix = new Color[width * height];
		for( int i = 0; i < pix.Length; ++i )
		{
			pix[ i ] = col;
		}
		Texture2D result = new Texture2D( width, height );
		result.SetPixels( pix );
		result.Apply();
		return result;
	}

	void update_mFoodName(){
		
		if (isBurgerVisible ()) {
			mFoodName = "Burger";
		}

		if (isLambVisible ()) {
			mFoodName = "Lamb";
		}

		if (isBaconVisible ()) {
			mFoodName = "Bacon";
		}

		if (isSushiVisible ()) {
			mFoodName = "Sushi";
		}

		if (isBSplitVisible()) {
			mFoodName = "BSplit";
		}
		Debug.Log (mFoodName);
	}

	bool isPlateVisible(){
		return myPlate.gameObject.GetComponent<Renderer> ().isVisible;
	}

	bool isBurgerVisible(){
		return myBurger.gameObject.GetComponent<Renderer> ().isVisible;
	}

	bool isLambVisible(){
		return myLambChops.gameObject.GetComponent<Renderer> ().isVisible;
	}

	bool isBaconVisible(){
		return myBacon.gameObject.GetComponent<Renderer> ().isVisible;
	}

	bool isSushiVisible(){
		return mySushi.gameObject.GetComponent<Renderer> ().isVisible;
	}

	bool isBSplitVisible(){
		return myBSplit.gameObject.GetComponent<Renderer> ().isVisible;
	}




}