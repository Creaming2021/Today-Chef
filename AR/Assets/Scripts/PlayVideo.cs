using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Video;

public class PlayVideo : MonoBehaviour
{
  public GameObject playButton;
  public GameObject video;

  // public void DoDelayAction()
  // {
  //   StartCoroutine(DelayAction(1));
  // }
 
  // IEnumerator DelayAction(float delayTime)
  // {
  //   //Wait for the specified delay time before continuing.
  //   yield return new WaitForSeconds(delayTime);
  
  //   //Do the action after the delay time has finished.
  //   Play();
  // }

  public void Pause(){
    video.GetComponent<VideoPlayer>().Pause();
    video.SetActive(false);
    Debug.Log("멈춤");
  }

  void Play(){
    playButton.SetActive(false);
    video.SetActive(true);
    // video.GetComponent<VideoPlayer>().Play();
  }

  void FixedUpdate () {
    if(Input.GetMouseButton(0)){
      GameObject target = GetClickedObject();
      Debug.Log("클릭");
      if(target != null && target.Equals(playButton)){
        Debug.Log("재생");
        Play();
      }
    }
  }
  
  GameObject GetClickedObject() {
    GameObject target = null; 
    RaycastHit hit;
    Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition); //마우스 포인트 근처 좌표를 만든다. 

    if(Physics.Raycast(ray.origin, ray.direction * 10, out hit)){
      target = hit.collider.gameObject; 
    } 
    return target; 
  } 
}
