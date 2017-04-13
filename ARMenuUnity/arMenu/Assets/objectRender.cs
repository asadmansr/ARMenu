using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class objectRender : MonoBehaviour {

	GameObject myHam, myCube, mySphr, myPlane, mySteam;
	int objIndex;
	Vector2 startPosition;
	float startTime;

	void Start () {
		myHam = GameObject.Find ("HamEgg");
		myCube = GameObject.Find ("cube");
		mySphr = GameObject.Find ("sphere");
		mySteam = GameObject.Find ("WhiteSmoke");

		//myPlane = GameObject.Find ("ARplane");
		//start with case 3
		objIndex = 3;
	}

	// Update is called once per frame
	void Update () {

//			if (gesture ()) {
//				objIndex++;
//				Debug.Log ("Swipe Detected");
//			}

		if (Input.GetMouseButtonDown (0)) {
			objIndex++;
			Debug.Log ("Left Click");
		}

		if (Input.GetMouseButtonDown (1)) {
			objIndex--;
			Debug.Log ("Right Click");		
		}

		if (objIndex == 4) {
			objIndex = 1;
		}

		if (objIndex == 0) {
			objIndex = 3;
		}

		switch (objIndex) {
		case 1:
			showHam (false);
			showCube (true);
			showSphr (false);
			showSteam (false);
			Debug.Log ("Case 1");
			break;
		case 2:
			showCube (false);
			showSphr (true);
			showHam (false);
			showSteam (false);
			Debug.Log ("Case 2");
			break;
		case 3:
			showSphr (false);
			showHam (true);
			showSteam (true);
			showCube (false);
			Debug.Log ("Case 3");
			break;
		}
	}


	void showHam(bool n){
		myHam.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showCube(bool n){
		myCube.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showSphr(bool n){
		mySphr.gameObject.GetComponent<Renderer> ().enabled = n;
	}

	void showSteam(bool n){
		mySteam.gameObject.GetComponent<Renderer> ().enabled = n;
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
