using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Video;

public class PlayVideo : MonoBehaviour
{
  public GameObject gameObject;

  public void DoDelayAction()
  {
    StartCoroutine(DelayAction(1));
  }
 
  IEnumerator DelayAction(float delayTime)
  {
    //Wait for the specified delay time before continuing.
    yield return new WaitForSeconds(delayTime);
  
    //Do the action after the delay time has finished.
    Play();
  }

  void Play(){
    gameObject.SetActive(true);
    gameObject.GetComponent<VideoPlayer>().Play();
  }
}
