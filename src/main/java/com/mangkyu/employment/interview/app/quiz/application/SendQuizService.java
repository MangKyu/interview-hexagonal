package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.SendQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveSendQuizHistoryPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SendQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendQuizService implements SendQuizUseCase {

    private final LoadMemberPort loadMemberPort;
    private final LoadQuizPort loadQuizPort;
    private final SaveMemberPort saveMemberPort;
    private final SendQuizPort sendQuizPort;
    private final SaveSendQuizHistoryPort sendQuizHistoryPort;

    @Override
    public int sendToEveryMember() {
        final QuizDay quizDay = QuizDay.findQuizDay(LocalDateTime.now());
        final List<Member> memberList = loadMemberPort.findEnabledMembers(quizDay);

        for (final Member member : memberList) {
            final UnsentQuizzes unsentQuizzes = loadQuizPort.findUnsentQuizzes(member);
            if (unsentQuizzes.isLast()) {
                member.disable();
                saveMemberPort.save(member);
            }

            sendQuizPort.send(unsentQuizzes);
            sendQuizHistoryPort.saveAll(member, unsentQuizzes.getRandomQuizUnderSize());
        }

        return memberList.size();
    }

}
