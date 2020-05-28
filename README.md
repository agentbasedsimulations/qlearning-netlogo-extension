# Q-Learning Netlogo Extension

This extension provides an easy way to use Q-Learning within Netlogo.

(TODO Eloísa: colocar um link no termo Q-Learning, apontando para algum local que explique ele. Pode ser a wikipedia)

**Table of Contents**
* [Installation](#installation)
* [Usage](#usage)
* [Example](#example)
* [Team](#team)


## Installation
Descrever como a extension pode ser instalada atraves do Extension Manager, mencionando a figura abaixo.

![NetLogo Extension Manager](img/netlogo-extension-manager.png)

## Usage

The extension provides a set of primitives to setup and execute the Q-Learning algorithm.

that must be executed during the **setup**, and another set of primitives that must be executed during the **execution** of your simulation.

### Q-Learning Setup Primitives
The **setup** primitives must be executed during the set up of your simulation. In NetLogo this is often done in a `setup` procedure.

It is **extremely recommended** to execute a `clear-all` in the setup procedure. If do not execute it, things will get duplicated and the extension may not work.

Then in the `setup` procedure you must execute an `ask` to the breed you want to be the learners. Inside this `ask` you can run the following primitives:

---

#### `qlearningextension:state-def ["var1" "varN"]`

Used to define the **state representation** of your learner agent.
This primitive receives a list containing variable names that the agent that did the `ask` owns. **Before running any of the primitives below you must first run this primitive**.

---

#### `qlearningextension:state-def ["var1" "varN"] reporter`

Acts exactly the previous primitive, except that it admits an additional `reporter` argument. This primitive is useful when you need to add values to the state definition that are not agent variables.

The `reporter` argument must be a reporter that returns a `string`.  Every time the extension is about to generate a state, this reporter will be called and its return will be added to the state definition of that state.

---

#### `(qlearningextension:action [action1] [action2] [actionN])`
Used to define what **actions** the learner agent can perform.

The primitive receives as argument(s) the action(s) that the agent can perform. You can pass how many actions you want, **but they must be procedures not reporters**.  

Please notice (and do not forget to type) the parentheses encapsulating the primitive call and the brackets surrounding each action.

---

#### `learningextension:reward [rewardFunc]`

Used to define a **reporter** that will return a number with the **reward** for the **current state**.

---

#### `qlearningextension:end-episode [isEndState] resetEpisode`

This primitive should be used in **episodic** learning to reset the problem to its initial state by the end of an episode.

The `isEndState` argument must be a `reporter` that returns a boolean value to indicate whether the current state characterizes the end of an episode. The `resetEpisode` argument must be a `procedure` that resets the agent/environment to its initial state. This `resetEpisode` procedure is called automatically by the extension when `isEndState` returns `true`.

---

#### `qlearningextension:action-selection "policy" []`

Used to define the action selection policy.

The following two selection policies are provided:
- `random-normal`: selects an action at random according to the percentage passed as argument. For example, `qlearningextension:action-selection "random-normal" [0.8]` specifies that 80% of the actions will be selected at random;

- `e-greedy`: also selects an action according to the percentage passed as the first argument, but such percentage is decreased over time according to the second argument. For example, `qlearningextension:action-selection "e-greedy" [0.8 0.99995]` specifies that 80% of the actions will be selected at random, but after each episode this percentage is updated and its new value corresponds to the current value multiplied by the decrease rate.

In both cases the numeric arguments must be between 0 and 1.

---

#### `qlearningextension:learning-rate learningRate`

Used to specify the learning rate. The `learningRate` argument must be a numeric value between 0 and 1.

---

#### `qlearningextension:discount-factor discountFactor`

Used to specify the discount factor. The  `discountFactor`argument must be a numeric value between 0 and 1.

---

### Q-Learning Execution Primitives

Now, with everything setted up you can run the simulation. In your "go" routine inside an `ask` to the learner agent you can run the primitive `qlearningextension:learning`. This will select an action to the current state, perform the action, get the reward, update the Q-table, verify if the new state is an end state and if so will run the procedure passed to the extension in the `end-episode` primitive.

To help you in debugging your simulations you can call the learning primitive in a little different way: `(qlearningextension:learning true)`, calling the primitive this way will make the extension print in the console the following values: The old state representation, the old Q-list (the Q-table values of the old state), the reward of the new state, the new state representation, the expected reward of the new state and the new Q-list. Another way to debugging your simulations calling the `qlearningextension:get-qtable` primitive, this will return a string with the current Q-table.

## Example

To make it easier to understand the usage of the extension we [implemented](cliff-walking.nlogo) the classic [cliff-waking problem](https://medium.com/@lgvaz/understanding-q-learning-the-cliff-walking-problem-80198921abbc) using the extension.

(TODO Eloisa: explicar o exemplo e o código. Pode adaptar do artigo do BRACIS, mas dai teria que refrasear algumas coisas para que os revisores não reclamem que o texto do artigo foi copiado daqui)

> Q-Learning example: the Cliff Walking problem

```NetLogo
extensions[qlearningextension]

Breed[Walkers Walker]

to setup
  ask Walkers [
    qlearningextension:state-def ["xcor" "ycor"]
    (qlearningextension:actions [goUp] [goDown] [goLeft] [goRight])
    qlearningextension:reward [rewardFunc]
    qlearningextension:end-episode [isEndState] resetEpisode
    qlearningextension:action-selection "e-greedy" [0.5 0.08]
    qlearningextension:learning-rate 1
    qlearningextension:discount-factor 0.75
  ]
end


to go
  ask Walkers [
    qlearningextension:learning
    print(qlearningextension:get-qtable)
  ]
end
```

# Team

[Kevin Kons](https://github.com/KevinKons) released the first version of the extension in 2019 as his software engineering bachelor's thesis at the [Universidade do Estado de Santa Catarina (UDESC)](https://www.udesc.br/ceavi).

Currently, the following team is in charge of maintaining the extension:
- [Eloísa Bazzanela](https://github.com/elobazza) (undergraduate software engineering student at UDESC)
- [Fernando Santos](https://github.com/santos-fernando) (professor at UDESC)
