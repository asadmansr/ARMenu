using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class testRender : MonoBehaviour {

	GameObject myBurger, myLambChops, myBacon,  myBSplit, mySushi, myPlate, myPlane;
	GameObject burgerName, lcName, bneName, bsplitName, sushiName;
	GameObject burgerPrice, lcPrice, bnePrice, bsplitPrice, sushiPrice;
	GameObject burgerSteam, lcSteam, bneSteam;

	int objIndex;
	Vector2 startPosition;
	float startTime;

	void Start () {

		myPlane = GameObject.Find ("ARplane");

		myPlate = GameObject.Find ("plate");

		myBurger = GameObject.Find ("Burger");
		burgerName = GameObject.Find ("burgerName");
		burgerPrice = GameObject.Find ("burgerPrice");
		burgerSteam = GameObject.Find ("burgerSteam");

		myLambChops = GameObject.Find ("Lamb Chops");
		lcName = GameObject.Find ("lcName");
		lcPrice = GameObject.Find ("lcPrice");
		lcSteam = GameObject.Find ("lcSteam");

		myBacon = GameObject.Find ("Bacon and Eggs");
		bneName = GameObject.Find ("bneName");
		bnePrice = GameObject.Find ("bnePrice");
		bneSteam = GameObject.Find ("bneSteam");
				 
		mySushi = GameObject.Find ("Sushi");
		sushiName = GameObject.Find ("sushiName");
		sushiPrice = GameObject.Find ("sushiPrice");

		myBSplit = GameObject.Find ("Banana Split");
		bsplitName = GameObject.Find ("bsplitName");
		bsplitPrice = GameObject.Find ("bsplitPrice");

		//start with case 1
		objIndex = 1;
	}
	
	// Update is called once per frame
	void Update () {

		if (myPlane.gameObject.GetComponent<Renderer> ().enabled == true) {

			if (gesture ()) {
				objIndex++;
				Debug.Log ("Swipe Detected");
			}

			//if (Input.GetMouseButtonDown (0)) {
			//	objIndex++;
			//	Debug.Log ("Left Click");
			//}

			//if (Input.GetMouseButtonDown (1)) {
			//	objIndex--;
			//	Debug.Log ("Right Click");		
			//}

			if (objIndex == 6) {
				objIndex = 1;
			}

			if (objIndex == 0) {
				objIndex = 5;
			}

			switch (objIndex) {
			case 1:
				showBurger (false);
				showLamb (true);
				showBacon (false);
				showBsplit (false);
				showSushi (false);
				showPlate (true);
				Debug.Log ("Case 1)");
				break;
			case 2:
				showLamb (false);
				showBacon (true);
				showBurger (false);
				showBsplit (false);
				showSushi (false);
				showPlate (true);
				Debug.Log ("Case 2");
				break;
			case 3:
				showBacon (false);
				showBurger (true);
				showLamb (false);
				showBsplit (false);
				showSushi (false);
				showPlate (true);
				Debug.Log ("Case 3");
				break;
			case 4:
				showBacon (false);
				showBurger (false);
				showLamb (false);
				showBsplit (true);
				showSushi (false);
				showPlate (false);
				Debug.Log ("Case 4");
				break;
			case 5:
				showBacon (false);
				showBurger (false);
				showLamb (false);
				showBsplit (false);
				showSushi (true);
				showPlate (false);
				Debug.Log ("Case 5");
				break;
			}
		}
	}

	void showPlate(bool n){
		myPlate.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showBurger(bool n){
		myBurger.gameObject.GetComponent<Renderer> ().enabled = n;
		burgerName.gameObject.GetComponent<Renderer> ().enabled = n;
		burgerPrice.gameObject.GetComponent<Renderer> ().enabled = n;
		burgerSteam.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showLamb(bool n){
		myLambChops.gameObject.GetComponent<Renderer> ().enabled = n;
		lcName.gameObject.GetComponent<Renderer> ().enabled = n;
		lcPrice.gameObject.GetComponent<Renderer> ().enabled = n;
		lcSteam.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showBacon(bool n){
		myBacon.gameObject.GetComponent<Renderer> ().enabled = n;
		bneName.gameObject.GetComponent<Renderer> ().enabled = n;
		bnePrice.gameObject.GetComponent<Renderer> ().enabled = n;
		bneSteam.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showSushi(bool n){
		mySushi.gameObject.GetComponent<Renderer> ().enabled = n;
		sushiName.gameObject.GetComponent<Renderer> ().enabled = n;
		sushiPrice.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showBsplit(bool n){
		myBSplit.gameObject.GetComponent<Renderer> ().enabled = n;
		bsplitName.gameObject.GetComponent<Renderer> ().enabled = n;
		bsplitPrice.gameObject.GetComponent<Renderer> ().enabled = n;
	}
		

	// gestures
	bool gesture(){

		//Get initial touch location and time
		if (Input.touchCount > 0 && Input.GetTouch (0).phase == TouchPhase.Began) {
			startPosition = Input.GetTouch(0).position;
			startTime = Time.time;
		}

		//Get final touch location and calculate swipe
		if (Input.touchCount > 0 && Input.GetTouch(0).phase == TouchPhase.Ended) {
			Vector2 endPosition = Input.GetTouch(0).position;
			Vector2 delta = endPosition - startPosition;

			float dist = Mathf.Sqrt(Mathf.Pow(delta.x, 2) + Mathf.Pow (delta.y, 2));
			float angle = Mathf.Atan (delta.y/delta.x) * (180.0f/Mathf.PI);
			float duration = Time.time - startTime;
			float speed = dist/duration;

			// Left to right swipe
			if (startPosition.y < endPosition.y) {
				if (angle < 0) angle = angle * 1.0f;

				if (dist > 300 &&  angle < 10 && speed > 1000) {

					return true;
				
				}
			}
		}

		return false;
	
	}



}
