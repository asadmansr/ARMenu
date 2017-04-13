using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class gestures : MonoBehaviour {

	Vector2 startPosition;
	float startTime;


	// Use this for initialization
	void Start () {

	}

	// Update is called once per frame
	void Update () {

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

					// **do something here**

				}

			}
		}
	}
}
