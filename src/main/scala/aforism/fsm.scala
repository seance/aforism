package aforism

import scalaz.{Functor, Monad, MonadState}
import monocle.{Lens, PLens}

trait MFSM {

  type M[_]
  type State

  def fsmState: Lens[State, M[Unit]]

  def isDone: M[Boolean]

  def transition(m: M[Unit])(implicit S: MonadState[M, State]) =
    S.modify(fsmState.set(m))

  def processState(implicit M: Monad[M], S: MonadState[M, State]): M[_] =
    M.bind(S.get)(fsmState.get)

  def process(implicit M: Monad[M], S: MonadState[M, State]): M[Unit] =
    M.untilM_(processState, isDone)
}

trait StateLensFunctions { self: MFSM =>

  def getL[A](lens: PLens[State, State, A, A])(implicit F: Functor[M], S: MonadState[M, State]) =
    F.map(S.get)(lens.get)

  def setL[A](lens: PLens[State, State, A, A])(value: A)(implicit S: MonadState[M, State]) =
    S.modify(lens.set(value))

  def modifyL[A, B](lens: PLens[State, State, A, B])(f: A => B)(implicit S: MonadState[M, State]) =
    S.modify(lens.modify(f))

  def mapL[A, B](lens: PLens[State, State, A, A])(f: A => B)(implicit F: Functor[M], S: MonadState[M, State]) =
    F.map(S.get)(f compose lens.get)
}

trait FSM extends MFSM with StateLensFunctions
