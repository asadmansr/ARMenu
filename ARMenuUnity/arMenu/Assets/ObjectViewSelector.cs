using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObjectViewSelector : MonoBehaviour {


	public static bool viewCube;
	public static bool viewHamEgg;
	public static bool viewSphere;
	private int itemSelector;
	private int objectCount;
	public objectScript[] ViewableObjects;

	// Use this for initialization
	void Start () {
		itemSelector = 1;
	}
	
	// Update is called once per frame
	void Update () {
		itemSelector = 1;
		Debug.Log (itemSelector);
		Debug.Log (viewCube);
		Debug.Log (viewHamEgg);
		Debug.Log (viewSphere);

		ViewableObjects = FindObjectsOfType (typeof(objectScript)) as objectScript[];
		objectCount = ViewableObjects.Length;

		if (Input.GetMouseButtonDown(0)) {
			itemSelector -= 1;
			Debug.Log ("Left Click");
		}

		if (Input.GetMouseButtonDown(1)) {
			itemSelector += 1;
			Debug.Log ("Right Click");
		}

		if (itemSelector >= 4) {
			itemSelector = 1;
		}

		if (itemSelector >= 0) {
			itemSelector = 3;
		}

		if (itemSelector == 1) {
			viewHamEgg = true;
		} else { 
			viewHamEgg = false;
		}

		if (itemSelector == 2) {
			viewCube = true;
		} else { 
			viewCube = false;
		}

		if (itemSelector == 3) {
			viewSphere = true;
		} else { 
			viewSphere = false;
		}


		for (int i = 0; i < objectCount; i++) {
			if (viewHamEgg) {
				if (ViewableObjects [i].gameObject.tag == "HamEgg") {
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = true;
				} else {
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = false;
				}
			} 

			if (viewCube) {
				if (ViewableObjects [i].gameObject.tag == "cube") {
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = true;
				} else {
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = false;
				}
			} 

			if (viewSphere) {
				if (ViewableObjects [i].gameObject.tag == "sphere"){
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = true;
				} else {
					ViewableObjects [i].gameObject.GetComponent<Renderer> ().enabled = false;
				}
			} 

		}


	}
}
