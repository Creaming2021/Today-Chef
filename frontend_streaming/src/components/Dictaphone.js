// import React from 'react'
// import SpeechRecognition, { useSpeechRecognition } from 'react-speech-recognition'

// const Dictaphone = () => {
//   const { transcript, resetTranscript } = useSpeechRecognition()

//   if (!SpeechRecognition.browserSupportsSpeechRecognition()) {
//     return null
//   }

//   return (
//     <div>
//       <button onClick={SpeechRecognition.startListening}>Start</button>
//       <button onClick={SpeechRecognition.stopListening}>Stop</button>
//       <button onClick={resetTranscript}>Reset</button>
//       <p>{transcript}</p>
//     </div>
//   )
// }
// export default Dictaphone

import React, { useState } from 'react'
import SpeechRecognition, { useSpeechRecognition } from 'react-speech-recognition'

const Dictaphone = ({ parentFunction, rootFunction }) => {

  SpeechRecognition.startListening({
      continuous: true,
      language: 'ko-KR', 
  });

  const [message, setMessage] = useState('');



  const childFunction = (transcript) => {
    parentFunction(transcript);
  };

  const toRootFunction = (data) => rootFunction(data);


  const commands = [
    {
      command: [
        '음소거', 
        '음소거 해제', 
        '마이크 켜',
        '마이크 꺼',
        '카메라 켜', 
        '카메라 꺼',
        '화면 켜',
        '화면 꺼', 
        '채팅 켜',
        '채팅 꺼',
        '채팅 창 켜',
        '채팅 창 꺼',
        '채팅창 켜',
        '채팅창 꺼',
      ],
      callback: (command) => {
        toRootFunction(command);
        resetTranscript();
      }
    },
    {
      command: 'I would like to order *',
      callback: (food) => setMessage(`Your order is for: ${food}`)
    },
    {
      command: 'The weather is :condition today',
      callback: (condition) => setMessage(`Today, the weather is ${condition}`)
    },
    {
      command: 'My top sports are * and *',
      callback: (sport1, sport2) => setMessage(`#1: ${sport1}, #2: ${sport2}`)
    },
    {
      command: 'Pass the salt (please)',
      callback: () => setMessage('My pleasure')
    },
    {
      command: 'Beijing',
      callback: (command, spokenPhrase, similarityRatio) => setMessage(`${command} and ${spokenPhrase} are ${similarityRatio * 100}% similar`),
      // If the spokenPhrase is "Benji", the message would be "Beijing and Benji are 40% similar"
      isFuzzyMatch: true,
      fuzzyMatchingThreshold: 0.2
    },
    {
      command: '클리어',
      callback: ({ resetTranscript }) => resetTranscript()
    },
    {
      command: '차민석',
      callback: (command) => setMessage(`최고의 남자 ${command}`),
      isFuzzyMatch: true,
      fuzzyMatchingThreshold: 0.2,
      bestMatchOnly: true
    },
    {
      command: '전송',
      callback: ({ resetTranscript }) => {
        setMessage(`데이터 전송`);
        // console.log(transcript);
        childFunction(transcript.slice(0, -2));
        resetTranscript();
      }
    },
  ];

  const { transcript, resetTranscript, finalTranscript } = useSpeechRecognition({ commands });

  if (finalTranscript != '') {
    if (commands[0].command.filter(obj => finalTranscript === obj).length == 0) {
      childFunction(finalTranscript);
      resetTranscript();
    }
  }


  if (!SpeechRecognition.browserSupportsSpeechRecognition()) {
    alert('음성 인식을 지원하지 않는 브라우저입니다. Chrome을 이용해주세요.');
  }

  return (
    <div>
      {/* <h1>{testMessage}</h1> */}
      {/* <button onClick={SpeechRecognition.stopListening}>Stop</button>
      <p>{message}</p>
      <p>{transcript}</p> */}
    </div>
  )
}
export default Dictaphone